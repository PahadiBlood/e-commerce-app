package xyz.manojraw.ecommerce.customer.dto.customer;


import jakarta.validation.constraints.NotBlank;
import xyz.manojraw.ecommerce.customer.dto.address.AddressRequestDto;

public record CustomerRequestDto(@NotBlank(message = "First name is mandatory") String firstName,
                                 @NotBlank(message = "Last name is mandatory") String lastName,
                                 @NotBlank(message = "Email is mandatory") String email,
                                 AddressRequestDto address) {
}
