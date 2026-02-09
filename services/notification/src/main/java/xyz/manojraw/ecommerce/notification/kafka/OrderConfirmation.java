package xyz.manojraw.ecommerce.notification.kafka;

import xyz.manojraw.ecommerce.notification.enumeration.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderConfirmation {

    private String orderReference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Customer customer;
    private List<Product> products;
}

