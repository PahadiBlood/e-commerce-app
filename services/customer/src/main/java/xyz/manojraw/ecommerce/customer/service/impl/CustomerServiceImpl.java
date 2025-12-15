package xyz.manojraw.ecommerce.customer.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.manojraw.ecommerce.common.exception.ApiException;
import xyz.manojraw.ecommerce.common.exception.CustomerNotFoundException;
import xyz.manojraw.ecommerce.customer.dto.customer.CustomerRequestDto;
import xyz.manojraw.ecommerce.customer.dto.customer.CustomerResponseDto;
import xyz.manojraw.ecommerce.customer.mapper.CustomerMapper;
import xyz.manojraw.ecommerce.customer.model.Customer;
import xyz.manojraw.ecommerce.customer.respository.CustomerRepository;
import xyz.manojraw.ecommerce.customer.service.CustomerService;

import java.util.List;


@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Page<CustomerResponseDto> getAll(int pageNo, int size) {
        pageNo = pageNo < 1 ? 0 : pageNo - 1;

        Pageable pageable = PageRequest.of(pageNo, size);
        Page<Customer> customers = customerRepository.findAll(pageable);

        if (!customers.isEmpty()) {
            List<CustomerResponseDto> customersDto = customerMapper.toListDto(customers.getContent());
            return new PageImpl<>(customersDto, pageable, customers.getTotalElements());
        }
        return Page.empty();
    }

    @Override
    public CustomerResponseDto getById(String id) {
        Customer customer = findById(id);
        return customerMapper.toDto(customer);
    }

    private Customer findById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found", "NOT_FOUND", HttpStatus.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponseDto save(CustomerRequestDto requestDto) {
        validateCustomer(requestDto);
        Customer customer = customerMapper.toEntity(requestDto);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Transactional
    @Override
    public CustomerResponseDto update(CustomerRequestDto requestDto, String id) {
        Customer customer = findById(id);
        if (!customer.getEmail().equals(requestDto.email())) {
            throw new ApiException("Email already in use by another customer", "CUSTOMER_EMAIL_CONFLICT", HttpStatus.CONFLICT);
        }
        CustomerResponseDto customerDto = buildCustomer(customer, requestDto);
        customerRepository.save(customer);
        return customerDto;
    }

    @Override
    public void deleteById(String id) {
        try {
            customerRepository.deleteById(id);
        } catch (org.springframework.dao.EmptyResultDataAccessException ex) {
            throw new ApiException("Customer not found", "CUSTOMER_NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ApiException("Customer cannot be deleted because it is referenced by other records.", "CUSTOMER_DELETE_CONFLICT", HttpStatus.CONFLICT);
        } catch (Exception ex) {
            throw new ApiException("Failed to delete customer.", "CUSTOMER_DELETE_FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateCustomer(CustomerRequestDto requestDto) {
        if (customerRepository.existsByEmail(requestDto.email())) {
            throw new ApiException("Customer already exists", "ALREADY_EXISTS", HttpStatus.CONFLICT);
        }
    }

    private CustomerResponseDto buildCustomer(Customer customer, CustomerRequestDto requestDto) {
        customer.setFirstName(requestDto.firstName());
        customer.setLastName(requestDto.lastName());
        return customerMapper.toDto(customer);
    }
}
