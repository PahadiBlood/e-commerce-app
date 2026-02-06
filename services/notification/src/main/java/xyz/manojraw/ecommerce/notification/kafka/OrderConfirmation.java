package xyz.manojraw.ecommerce.notification.kafka;

import xyz.manojraw.ecommerce.notification.enumeration.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
