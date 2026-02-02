package xyz.manojraw.ecommerce.order.service;

import jakarta.validation.Valid;
import xyz.manojraw.ecommerce.order.dto.OrderRequestDto;

public interface OrderService {
    Integer save(@Valid OrderRequestDto requestDto);
}
