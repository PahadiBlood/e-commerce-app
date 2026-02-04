package xyz.manojraw.ecommerce.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.manojraw.ecommerce.order.dto.OrderLineRequestDto;
import xyz.manojraw.ecommerce.order.dto.OrderLineResponseDto;
import xyz.manojraw.ecommerce.order.mapper.OrderLineMapper;
import xyz.manojraw.ecommerce.order.repository.OrderLineRepository;
import xyz.manojraw.ecommerce.order.service.OrderLineService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderLineServiceImpl implements OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    @Override
    public Long save(OrderLineRequestDto requestDto) {
        var orderLine = mapper.toEntity(requestDto);
        return orderLineRepository.save(orderLine).getId();
    }

    @Override
    public List<OrderLineResponseDto> getAllByOrderId(Long orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toDto);
    }
}
