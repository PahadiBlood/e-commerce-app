package xyz.manojraw.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.manojraw.ecommerce.order.model.OrderLine;

import java.nio.channels.FileChannel;
import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findAllByOrderId(Long orderId);
}
