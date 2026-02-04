package xyz.manojraw.ecommerce.order.service;

import org.springframework.data.domain.Page;
import xyz.manojraw.ecommerce.order.dto.OrderRequestDto;
import xyz.manojraw.ecommerce.order.dto.OrderResponseDto;

public interface OrderService {
    Long save(OrderRequestDto requestDto);

    Page<OrderResponseDto> getAll(int pageNo, int size);

    OrderResponseDto getById(Long id);
}
