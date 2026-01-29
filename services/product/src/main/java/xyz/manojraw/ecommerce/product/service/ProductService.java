package xyz.manojraw.ecommerce.product.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import xyz.manojraw.ecommerce.product.dto.product.ProductPurchaseRequestDto;
import xyz.manojraw.ecommerce.product.dto.product.ProductPurchaseResponseDto;
import xyz.manojraw.ecommerce.product.dto.product.ProductRequestDto;
import xyz.manojraw.ecommerce.product.dto.product.ProductResponseDto;

import java.util.List;

public interface ProductService {
    Page<ProductResponseDto> getAll(int pageNo, int size);

    ProductResponseDto getById(long id);

    ProductResponseDto save(@Valid ProductRequestDto requestDto);

    ProductResponseDto update(ProductRequestDto requestDto, long id);

    List<ProductPurchaseResponseDto> purchase(List<ProductPurchaseRequestDto> purchaseRequestDto);

    void deleteById(long id);
}
