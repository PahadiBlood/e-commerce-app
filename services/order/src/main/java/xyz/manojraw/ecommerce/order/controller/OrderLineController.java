package xyz.manojraw.ecommerce.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.manojraw.ecommerce.order.dto.OrderLineResponseDto;
import xyz.manojraw.ecommerce.order.service.impl.OrderLineServiceImpl;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {

    private final OrderLineServiceImpl orderLineServiceImpl;

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderLineResponseDto>> getAllByOrderId(@PathVariable Long orderId){
        return ResponseEntity.ok(orderLineServiceImpl.getAllByOrderId(orderId));
    }
}
