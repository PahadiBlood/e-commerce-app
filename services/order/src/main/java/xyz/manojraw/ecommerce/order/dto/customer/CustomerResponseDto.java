package xyz.manojraw.ecommerce.order.dto.customer;

public record CustomerResponseDto(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
