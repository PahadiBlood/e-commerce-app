package xyz.manojraw.ecommerce.payment.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record CustomerDto(
        Long id,
        @NotBlank(message = "First name is mandatory")
        String firstName,
        @NotBlank(message = "Last name is mandatory")
        String lastName,
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Invalid email")
        String email
) {
}
