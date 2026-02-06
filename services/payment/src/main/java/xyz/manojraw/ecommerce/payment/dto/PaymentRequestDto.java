package xyz.manojraw.ecommerce.payment.dto;

import xyz.manojraw.ecommerce.payment.dto.customer.CustomerDto;
import xyz.manojraw.ecommerce.payment.enumuration.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequestDto(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerDto customer
) {
}
