package xyz.manojraw.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.manojraw.product.dto.product.ProductPurchaseRequestDto;
import xyz.manojraw.product.dto.product.ProductPurchaseResponseDto;
import xyz.manojraw.product.dto.product.ProductRequestDto;
import xyz.manojraw.product.dto.product.ProductResponseDto;
import xyz.manojraw.product.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getAll(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.getAll(pageNo, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@Valid @RequestBody ProductRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(requestDto));
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> update(@Valid @RequestBody ProductRequestDto requestDto, @PathVariable long id) {
        return ResponseEntity.ok(productService.update(requestDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponseDto>> purchase(@RequestBody List<ProductPurchaseRequestDto> requestDtoList) {
        return ResponseEntity.ok(productService.purchase(requestDtoList));
    }
}
