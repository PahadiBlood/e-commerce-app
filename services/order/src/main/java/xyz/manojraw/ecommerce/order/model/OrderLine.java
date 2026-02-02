package xyz.manojraw.ecommerce.order.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

@EntityListeners(AuditingEntityListener.class)
@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
