package xyz.manojraw.ecommerce.order.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import xyz.manojraw.ecommerce.order.dto.OrderRequestDto;
import xyz.manojraw.ecommerce.order.dto.OrderResponseDto;

public interface OrderService {
    Long save(@Valid OrderRequestDto requestDto);

    Page<OrderResponseDto> findAll(int pageNo, int size);
}
