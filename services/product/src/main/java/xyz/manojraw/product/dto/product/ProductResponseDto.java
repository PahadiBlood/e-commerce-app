package xyz.manojraw.product.dto.product;

import xyz.manojraw.product.dto.category.CategoryResponseDto;

import java.math.BigDecimal;

public record ProductResponseDto(
        Long id,
        String name,
        String description,
        int availableQuantity,
        BigDecimal price,
        CategoryResponseDto category
) {
}
