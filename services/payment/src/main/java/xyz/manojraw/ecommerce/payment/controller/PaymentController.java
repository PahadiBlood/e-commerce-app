package xyz.manojraw.ecommerce.payment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.manojraw.ecommerce.payment.dto.PaymentRequestDto;
import xyz.manojraw.ecommerce.payment.service.PaymentService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody PaymentRequestDto requestDto){
        return ResponseEntity.ok(paymentService.save(requestDto));
    }
}
