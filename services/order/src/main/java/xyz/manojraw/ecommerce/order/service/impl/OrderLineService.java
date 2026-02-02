package xyz.manojraw.ecommerce.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.manojraw.ecommerce.order.dto.OrderLineRequestDto;
import xyz.manojraw.ecommerce.order.mapper.OrderLineMapper;
import xyz.manojraw.ecommerce.order.repository.OrderLineRepository;

@RequiredArgsConstructor
@Service
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Long save(OrderLineRequestDto requestDto) {
        var orderLine = mapper.toEntity(requestDto);
        return orderLineRepository.save(orderLine).getId();
    }
}
