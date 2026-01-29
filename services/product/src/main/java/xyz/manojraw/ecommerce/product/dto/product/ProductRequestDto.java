package xyz.manojraw.ecommerce.product.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDto(
        @NotBlank(message = "Name is mandatory") String name,
        String description,
        @Min(value = 0, message = "Available quantity must be greater than equals 0") int availableQuantity,
        @Min(value = 0, message = "Price must be greater than equals 0") BigDecimal price,
        @NotNull(message = "Category is mandatory for the product")
        Long categoryId
) {
}
