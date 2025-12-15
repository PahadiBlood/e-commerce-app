package xyz.manojraw.ecommerce.customer.mapper;

import org.mapstruct.Mapper;
import xyz.manojraw.ecommerce.customer.dto.customer.CustomerRequestDto;
import xyz.manojraw.ecommerce.customer.dto.customer.CustomerResponseDto;
import xyz.manojraw.ecommerce.customer.model.Customer;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDto toDto(Customer customer);

    Customer toEntity(CustomerRequestDto customerRequestDto);

    List<CustomerResponseDto> toListDto(List<Customer> customers);
}
