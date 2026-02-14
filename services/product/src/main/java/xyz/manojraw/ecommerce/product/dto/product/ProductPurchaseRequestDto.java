package xyz.manojraw.ecommerce.product.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequestDto(
        @NotNull(message = "Product id is mandatory") Long productId,
        @Min(value = 1, message = "At-least 1 product should be purchased") int quantity
) {
}
