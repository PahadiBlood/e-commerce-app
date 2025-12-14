package xyz.manojraw.ecommerce.customer.service;

import xyz.manojraw.ecommerce.customer.dto.customer.CustomerRequestDto;
import xyz.manojraw.ecommerce.customer.dto.customer.CustomerResponseDto;

public interface CustomerService {
    CustomerResponseDto save(CustomerRequestDto requestDto);
}
