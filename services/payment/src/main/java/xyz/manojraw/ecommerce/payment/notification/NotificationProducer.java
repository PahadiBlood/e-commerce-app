package xyz.manojraw.ecommerce.payment.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import xyz.manojraw.ecommerce.payment.dto.PaymentNotificationRequestDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate<String, PaymentNotificationRequestDto> kafkaTemplate;

    public void sendNotification(PaymentNotificationRequestDto paymentNotification) {
        log.info("Sending notification with body <{}>", paymentNotification);
        Message<PaymentNotificationRequestDto> message =
                MessageBuilder
                        .withPayload(paymentNotification)
                        .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                        .build();
        kafkaTemplate.send(message);
    }
}
