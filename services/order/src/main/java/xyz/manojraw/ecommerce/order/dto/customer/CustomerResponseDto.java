package xyz.manojraw.ecommerce.order.dto.customer;

public record CustomerResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
