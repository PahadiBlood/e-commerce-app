package xyz.manojraw.ecommerce.notification.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .createdAt(Instant.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        //sending email
        String customerName = paymentConfirmation.customerEmail() + " " + paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = {"order-topic"})
    public void consumeOrderNotification(OrderConfirmation orderConfirmation) {
        log.info("Consuming Order notification <{}>", orderConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.ORDER_CONFIRMATION)
                        .createdAt(Instant.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        //sending email
        String customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
        emailService.sendOrderSuccessEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.amount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
