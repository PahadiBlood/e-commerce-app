package xyz.manojraw.ecommerce.notification.kafka;

import java.math.BigDecimal;

public record Product(
        Long id,
        String name,
        String description,
        BigDecimal price,
        int quantity
) {
}
