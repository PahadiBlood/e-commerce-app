package xyz.manojraw.ecommerce.customer.mapper;

import org.mapstruct.Mapper;
import xyz.manojraw.ecommerce.customer.dto.customer.CustomerRequestDto;
import xyz.manojraw.ecommerce.customer.dto.customer.CustomerResponseDto;
import xyz.manojraw.ecommerce.customer.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDto toDto(Customer customer);

    Customer toEntity(CustomerRequestDto customerRequestDto);
}
