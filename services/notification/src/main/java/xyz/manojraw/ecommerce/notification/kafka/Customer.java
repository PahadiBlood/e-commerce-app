package xyz.manojraw.ecommerce.notification.kafka;

public record Customer(
        Long id,
        String firstName,
        String lastName,
        String email) {
}
