package xyz.manojraw.ecommerce.customer.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.manojraw.ecommerce.customer.dto.customer.CustomerRequestDto;
import xyz.manojraw.ecommerce.customer.dto.customer.CustomerResponseDto;
import xyz.manojraw.ecommerce.customer.mapper.CustomerMapper;
import xyz.manojraw.ecommerce.customer.model.Customer;
import xyz.manojraw.ecommerce.customer.respository.CustomerRepository;
import xyz.manojraw.ecommerce.customer.service.CustomerService;


@AllArgsConstructor

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDto save(CustomerRequestDto requestDto) {
        validateCustomer(requestDto);
        Customer customer = customerMapper.toEntity(requestDto);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    private void validateCustomer(CustomerRequestDto requestDto) {
//        if (customerRepository.existsByEmail(requestDto.email())) {
//            // exception handler will be added in future
//            throw new ValidationException(ApiError.USER_ALREADY_EXISTS);
//        }
    }
}
