package xyz.manojraw.ecommerce.order.mapper;

import org.mapstruct.Mapper;
import xyz.manojraw.ecommerce.order.dto.OrderLineRequestDto;
import xyz.manojraw.ecommerce.order.model.OrderLine;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {

    OrderLine toEntity(OrderLineRequestDto requestDto);
}
