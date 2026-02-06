package xyz.manojraw.ecommerce.notification.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import xyz.manojraw.ecommerce.notification.enumeration.NotificationType;
import xyz.manojraw.ecommerce.notification.kafka.OrderConfirmation;
import xyz.manojraw.ecommerce.notification.kafka.PaymentConfirmation;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Notification {

    @Id
    private Long id;
    private NotificationType notificationType;
    private Instant createdAt;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
