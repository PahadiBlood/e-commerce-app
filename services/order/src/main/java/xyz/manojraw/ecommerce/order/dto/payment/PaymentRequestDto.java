package xyz.manojraw.ecommerce.order.dto.payment;

import xyz.manojraw.ecommerce.order.dto.customer.CustomerResponseDto;
import xyz.manojraw.ecommerce.order.enumuration.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequestDto(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerResponseDto customer
) {
}
