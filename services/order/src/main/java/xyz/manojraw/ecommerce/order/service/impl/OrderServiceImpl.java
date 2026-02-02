package xyz.manojraw.ecommerce.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import xyz.manojraw.ecommerce.exception.ApiException;
import xyz.manojraw.ecommerce.order.client.CustomerClient;
import xyz.manojraw.ecommerce.order.client.ProductClient;
import xyz.manojraw.ecommerce.order.dto.OrderLineRequestDto;
import xyz.manojraw.ecommerce.order.dto.OrderRequestDto;
import xyz.manojraw.ecommerce.order.mapper.OrderMapper;
import xyz.manojraw.ecommerce.order.product.PurchaseRequest;
import xyz.manojraw.ecommerce.order.repository.OrderRepository;
import xyz.manojraw.ecommerce.order.service.OrderService;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    @Override
    public Integer save(OrderRequestDto requestDto) {
        //check the customer
        var customer = customerClient.getById(requestDto.customerId()).orElseThrow(() -> new ApiException("Customer not found", "NOT_FOUND", HttpStatus.NOT_FOUND));

        //purchase the product
        productClient.purchaseProducts(requestDto.products());
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

        return null;
    }
}
