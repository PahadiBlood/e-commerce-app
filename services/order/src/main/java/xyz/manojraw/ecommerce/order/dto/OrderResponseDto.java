package xyz.manojraw.ecommerce.order.dto;

import xyz.manojraw.ecommerce.order.enumuration.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponseDto(
        Long id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long customerId
) {
}
