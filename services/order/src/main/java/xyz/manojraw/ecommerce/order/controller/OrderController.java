package xyz.manojraw.ecommerce.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.manojraw.ecommerce.order.dto.OrderRequestDto;
import xyz.manojraw.ecommerce.order.dto.OrderResponseDto;
import xyz.manojraw.ecommerce.order.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody OrderRequestDto requestDto) {
        return ResponseEntity.ok(orderService.save(requestDto));
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponseDto>> getAll(@RequestParam(required = false, defaultValue = "0") int pageNo, @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(orderService.getAll(pageNo, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }
}
