package xyz.manojraw.ecommerce.payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.manojraw.ecommerce.payment.dto.PaymentNotificationRequestDto;
import xyz.manojraw.ecommerce.payment.dto.PaymentRequestDto;
import xyz.manojraw.ecommerce.payment.mapper.PaymentMapper;
import xyz.manojraw.ecommerce.payment.notification.NotificationProducer;
import xyz.manojraw.ecommerce.payment.repository.PaymentRepository;
import xyz.manojraw.ecommerce.payment.service.PaymentService;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    @Override
    public Long save(PaymentRequestDto requestDto) {
        var payment = paymentRepository.save(mapper.toEntity(requestDto));

        //send payment notification
        notificationProducer.sendNotification(
                new PaymentNotificationRequestDto(
                        requestDto.orderReference(),
                        requestDto.amount(),
                        requestDto.paymentMethod(),
                        requestDto.customer().firstName(),
                        requestDto.customer().lastName(),
                        requestDto.customer().email()
                )
        );
        return payment.getId();
    }
}
