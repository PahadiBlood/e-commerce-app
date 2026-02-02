package xyz.manojraw.ecommerce.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.manojraw.ecommerce.order.dto.customer.CustomerResponseDto;

import java.util.Optional;

@FeignClient(
        name = "customer-service", url = "${application.config.customer-url}"
)
public interface CustomerClient {

    @GetMapping("/{id}")
    Optional<CustomerResponseDto> getById(@PathVariable Long id);
}
