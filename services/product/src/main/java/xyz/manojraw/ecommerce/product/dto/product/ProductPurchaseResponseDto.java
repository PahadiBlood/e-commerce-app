package xyz.manojraw.ecommerce.product.dto.product;

import xyz.manojraw.ecommerce.product.dto.category.CategoryBasicInfoDto;

import java.math.BigDecimal;

public record ProductPurchaseResponseDto(
        Long id,
        String name,
        String description,
        int quantity,
        BigDecimal price,
        CategoryBasicInfoDto category
) {
}
