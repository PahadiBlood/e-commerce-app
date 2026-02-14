package xyz.manojraw.ecommerce.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import xyz.manojraw.ecommerce.order.enumuration.PaymentMethod;
import xyz.manojraw.ecommerce.order.product.PurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequestDto(
        Long id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Missing payment method")
        PaymentMethod paymentMethod,

        @NotNull(message = "Missing customer id")
        String customerId,

        @NotEmpty(message = "Purchase at-least one product")
        List<PurchaseRequest> products
) {
}
