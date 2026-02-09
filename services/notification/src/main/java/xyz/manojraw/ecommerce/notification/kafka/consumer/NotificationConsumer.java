package xyz.manojraw.ecommerce.notification.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import xyz.manojraw.ecommerce.notification.email.EmailService;
import xyz.manojraw.ecommerce.notification.enumeration.NotificationType;
import xyz.manojraw.ecommerce.notification.kafka.OrderConfirmation;
import xyz.manojraw.ecommerce.notification.kafka.PaymentConfirmation;
import xyz.manojraw.ecommerce.notification.model.Notification;
import xyz.manojraw.ecommerce.notification.repository.NotificationRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
        log.info("Consuming payment notification <{}>", paymentConfirmation);

        Notification notification = buildNotification(NotificationType.PAYMENT_CONFIRMATION);
        notification.setPaymentConfirmation(paymentConfirmation);

        notificationRepository.save(notification);

        //sending email
        String customerName = paymentConfirmation.getCustomerFirstName() + " " + paymentConfirmation.getCustomerLastName();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.getCustomerEmail(),
                customerName,
                paymentConfirmation.getAmount(),
                paymentConfirmation.getOrderReference()
        );
    }

    @KafkaListener(topics = {"order-topic"})
    public void consumeOrderNotification(OrderConfirmation orderConfirmation) {
        log.info("Consuming Order notification <{}>", orderConfirmation);

        Notification notification = buildNotification(NotificationType.ORDER_CONFIRMATION);
        notification.setOrderConfirmation(orderConfirmation);

        notificationRepository.save(notification);

        //sending email
        String customerName = orderConfirmation.getCustomer().getFirstName() + " " + orderConfirmation.getCustomer().getLastName();
        emailService.sendOrderSuccessEmail(
                orderConfirmation.getCustomer().getEmail(),
                customerName,
                orderConfirmation.getAmount(),
                orderConfirmation.getOrderReference(),
                orderConfirmation.getProducts()
        );
    }

    private static @NonNull Notification buildNotification(NotificationType notificationType) {
        Notification notification = new Notification();
        notification.setNotificationType(notificationType);
        notification.setCreatedAt(Instant.now());
        return notification;
    }
}
