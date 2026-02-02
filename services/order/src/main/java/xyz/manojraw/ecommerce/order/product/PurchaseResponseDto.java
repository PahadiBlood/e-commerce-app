package xyz.manojraw.ecommerce.order.product;

import java.math.BigDecimal;

public record PurchaseResponseDto(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        int quantity
) {
}
