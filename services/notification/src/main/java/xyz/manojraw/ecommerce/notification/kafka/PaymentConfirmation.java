package xyz.manojraw.ecommerce.notification.kafka;

import xyz.manojraw.ecommerce.notification.enumeration.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
