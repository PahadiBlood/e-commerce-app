package xyz.manojraw.ecommerce.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.manojraw.ecommerce.customer.dto.customer.CustomerRequestDto;
import xyz.manojraw.ecommerce.customer.dto.customer.CustomerResponseDto;
import xyz.manojraw.ecommerce.customer.service.CustomerService;


@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<Page<CustomerResponseDto>> getAll(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(customerService.getAll(pageNo, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> save(@Valid @RequestBody CustomerRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> update(@Valid @RequestBody CustomerRequestDto requestDto, @PathVariable String id) {
        return ResponseEntity.ok(customerService.update(requestDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
