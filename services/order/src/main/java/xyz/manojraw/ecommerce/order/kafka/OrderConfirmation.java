package xyz.manojraw.ecommerce.order.kafka;

import xyz.manojraw.ecommerce.order.dto.customer.CustomerResponseDto;
import xyz.manojraw.ecommerce.order.enumuration.PaymentMethod;
import xyz.manojraw.ecommerce.order.product.PurchaseResponseDto;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        CustomerResponseDto customer,
        List<PurchaseResponseDto> products
) {
}
