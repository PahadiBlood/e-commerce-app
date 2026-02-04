package xyz.manojraw.ecommerce.order.service;

import xyz.manojraw.ecommerce.order.dto.OrderLineRequestDto;
import xyz.manojraw.ecommerce.order.dto.OrderLineResponseDto;

import java.util.List;

public interface OrderLineService {
    Long save(OrderLineRequestDto requestDto);

    List<OrderLineResponseDto> getAllByOrderId(Long orderId);
}
