package xyz.manojraw.ecommerce.order.mapper;

import org.mapstruct.Mapper;
import xyz.manojraw.ecommerce.order.dto.OrderRequestDto;
import xyz.manojraw.ecommerce.order.dto.OrderResponseDto;
import xyz.manojraw.ecommerce.order.model.Order;


@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderRequestDto OrderRequestDto);
    OrderResponseDto toDto(Order order);
}
