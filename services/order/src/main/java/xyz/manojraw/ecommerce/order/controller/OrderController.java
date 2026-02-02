package xyz.manojraw.ecommerce.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.manojraw.ecommerce.order.dto.OrderRequestDto;
import xyz.manojraw.ecommerce.order.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> save(@Valid @RequestBody OrderRequestDto requestDto) {
        return ResponseEntity.ok(orderService.save(requestDto));
    }
}
