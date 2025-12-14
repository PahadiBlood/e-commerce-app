package xyz.manojraw.ecommerce.customer.dto.customer;

import xyz.manojraw.ecommerce.customer.dto.address.AddressResponseDto;

public record CustomerResponseDto(String id, String firstName, String lastName, String email,
                                  AddressResponseDto address) {
}
