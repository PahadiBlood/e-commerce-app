package xyz.manojraw.ecommerce.payment.mapper;

import org.springframework.stereotype.Component;
import xyz.manojraw.ecommerce.payment.dto.PaymentRequestDto;
import xyz.manojraw.ecommerce.payment.model.Payment;

@Component
public class PaymentMapper {
    public Payment toEntity(PaymentRequestDto requestDto) {
        Payment payment = new Payment();
        payment.setPaymentMethod(requestDto.paymentMethod());
        payment.setAmount(requestDto.amount());
        payment.setOrderId(requestDto.orderId());
        return payment;
    }
}
