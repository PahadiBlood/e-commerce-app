package xyz.manojraw.ecommerce.payment.service;

import xyz.manojraw.ecommerce.payment.dto.PaymentRequestDto;

public interface PaymentService {
    Long save(PaymentRequestDto requestDto);
}
