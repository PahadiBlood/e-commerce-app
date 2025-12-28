package xyz.manojraw.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.manojraw.product.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
