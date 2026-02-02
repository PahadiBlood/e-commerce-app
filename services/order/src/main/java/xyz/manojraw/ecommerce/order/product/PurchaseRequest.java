package xyz.manojraw.ecommerce.order.product;

import jakarta.validation.constraints.Min;

public record PurchaseRequest(
        @Min(value = 1, message = "Product id is mandatory")
        Long productId,
        @Min(value = 1, message = "Minimum 1 quantity is required")
        int quantity
) {

}
