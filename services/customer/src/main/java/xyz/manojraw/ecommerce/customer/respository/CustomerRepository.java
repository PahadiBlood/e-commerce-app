package xyz.manojraw.ecommerce.customer.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.manojraw.ecommerce.customer.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    boolean existsByEmail(String email);
}
