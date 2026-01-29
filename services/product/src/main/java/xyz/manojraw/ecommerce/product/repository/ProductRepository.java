package xyz.manojraw.ecommerce.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.manojraw.ecommerce.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
