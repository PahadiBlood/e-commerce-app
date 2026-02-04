package xyz.manojraw.ecommerce.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import xyz.manojraw.ecommerce.exception.ApiException;
import xyz.manojraw.ecommerce.order.client.CustomerClient;
import xyz.manojraw.ecommerce.order.client.ProductClient;
import xyz.manojraw.ecommerce.order.dto.OrderLineRequestDto;
import xyz.manojraw.ecommerce.order.dto.OrderRequestDto;
import xyz.manojraw.ecommerce.order.dto.OrderResponseDto;
import xyz.manojraw.ecommerce.order.kafka.OrderConfirmation;
import xyz.manojraw.ecommerce.order.kafka.OrderProducer;
import xyz.manojraw.ecommerce.order.mapper.OrderMapper;
import xyz.manojraw.ecommerce.order.repository.OrderRepository;
import xyz.manojraw.ecommerce.order.service.OrderLineService;
import xyz.manojraw.ecommerce.order.service.OrderService;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    @Override
    public Long save(OrderRequestDto requestDto) {
        //check the customer
        var customer = customerClient.getById(requestDto.customerId()).orElseThrow(() -> new ApiException("Customer not found", "NOT_FOUND", HttpStatus.NOT_FOUND));

        //purchase the product
        var purchaseProduct = productClient.purchaseProducts(requestDto.products());
        var order = orderRepository.save(mapper.toEntity(requestDto));

        //persist the order lines
        for (var p : requestDto.products()) {
            orderLineService.save(
                    new OrderLineRequestDto(
                            order.getId(),
                            p.productId(),
                            p.quantity()
                    )
            );
        }

        //start payment process
        //todo payment process

        //send order confirmation notification(kafka)
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                requestDto.reference(),
                requestDto.amount(),
                requestDto.paymentMethod(),
                customer,
                purchaseProduct
        ));
        return order.getId();
    }

    @Override
    public Page<OrderResponseDto> getAll(int pageNo, int size) {
        if (pageNo < 2) pageNo = 0;
        if (size < 1) size = 10;

        Pageable pageable = PageRequest.of(pageNo, size);
        var orders = orderRepository.findAll(pageable);

        var orderDtos = orderRepository.findAll(pageable).stream()
                .map(mapper::toDto)
                .toList();
        return new PageImpl<>(orderDtos, pageable, orders.getTotalElements());
    }

    @Override
    public OrderResponseDto getById(Long id) {
        return orderRepository
                .findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ApiException("Order not found", "NOT_FOUND", HttpStatus.NOT_FOUND));
    }
}
