package xyz.manojraw.ecommerce.payment.dto;

import xyz.manojraw.ecommerce.payment.enumuration.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequestDto(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
