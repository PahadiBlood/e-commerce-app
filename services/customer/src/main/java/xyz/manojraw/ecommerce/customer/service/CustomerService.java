package xyz.manojraw.ecommerce.customer.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import xyz.manojraw.ecommerce.customer.dto.customer.CustomerRequestDto;
import xyz.manojraw.ecommerce.customer.dto.customer.CustomerResponseDto;

public interface CustomerService {
    CustomerResponseDto save(CustomerRequestDto requestDto);

    Page<CustomerResponseDto> getAll(int pageNo, int size);

    CustomerResponseDto getById(String id);

    CustomerResponseDto update(@Valid CustomerRequestDto requestDto, String id);

    void deleteById(String id);
}
