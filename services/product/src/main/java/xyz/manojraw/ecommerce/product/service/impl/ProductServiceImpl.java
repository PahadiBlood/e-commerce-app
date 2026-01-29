package xyz.manojraw.ecommerce.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.manojraw.ecommerce.common.exception.ApiException;
import xyz.manojraw.ecommerce.common.exception.ProductNotFoundException;
import xyz.manojraw.ecommerce.product.dto.category.CategoryBasicInfoDto;
import xyz.manojraw.ecommerce.product.dto.product.ProductPurchaseRequestDto;
import xyz.manojraw.ecommerce.product.dto.product.ProductPurchaseResponseDto;
import xyz.manojraw.ecommerce.product.dto.product.ProductRequestDto;
import xyz.manojraw.ecommerce.product.dto.product.ProductResponseDto;
import xyz.manojraw.ecommerce.product.mapper.ProductMapper;
import xyz.manojraw.ecommerce.product.model.Category;
import xyz.manojraw.ecommerce.product.model.Product;
import xyz.manojraw.ecommerce.product.repository.ProductRepository;
import xyz.manojraw.ecommerce.product.service.CategoryService;
import xyz.manojraw.ecommerce.product.service.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    @Override
    public Page<ProductResponseDto> getAll(int pageNo, int size) {
        pageNo = pageNo < 1 ? 0 : pageNo - 1;

        Pageable pageable = PageRequest.of(pageNo, size);
        Page<Product> products = productRepository.findAll(pageable);

        if (!products.isEmpty()) {
            List<ProductResponseDto> productDos = productMapper.toListDto(products.getContent());
            return new PageImpl<>(productDos, pageable, products.getTotalElements());
        }
        return Page.empty();
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponseDto getById(long id) {
        Product product = findById(id);
        return productMapper.toDto(product);
    }

    private Product findById(long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found", "NOT_FOUND", HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Override
    public ProductResponseDto save(ProductRequestDto requestDto) {
        Category category = categoryService.getById(requestDto.categoryId());

        Product product = productMapper.toEntity(requestDto);
        product.setCategory(category);

        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Transactional
    @Override
    public ProductResponseDto update(ProductRequestDto requestDto, long id) {
        Product product = findById(id);
        if (!product.getCategory().getId().equals(requestDto.categoryId())) {
            Category category = categoryService.getById(requestDto.categoryId());
            product.setCategory(category);
        }
        product.setName(requestDto.name());
        product.setDescription(requestDto.description());
        product.setPrice(requestDto.price());
        product.setAvailableQuantity(requestDto.availableQuantity());

        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Transactional
    @Override
    public List<ProductPurchaseResponseDto> purchase(List<ProductPurchaseRequestDto> purchaseRequestDto) {

        List<Long> productIds = new ArrayList<>();
        Map<Long, Integer> productQuantityMap = new HashMap<>();

        for (ProductPurchaseRequestDto purchaseRequest : purchaseRequestDto) {
            productIds.add(purchaseRequest.id());
            productQuantityMap.put(purchaseRequest.id(), purchaseRequest.quantity());
        }

        List<Product> products = productRepository.findAllById(productIds);

        if (products.isEmpty()) {
            throw new ApiException("Invalid request", "INVALID_PRODUCT_IDS", HttpStatus.BAD_REQUEST);
        }

        if (products.size() != productIds.size()) {
            throw new ApiException("One or more product does not exist", "INVALID_PRODUCT_REQUEST", HttpStatus.BAD_REQUEST);
        }

        return products
                .stream()
                .map(p -> toPurchaseDto(p, productQuantityMap.get(p.getId()))).toList();
    }

    private ProductPurchaseResponseDto toPurchaseDto(Product product, int requiredQuantity) {
        int productAvailableQuantity = product.getAvailableQuantity();
        int remainingQuantity = productAvailableQuantity - requiredQuantity;
        if (remainingQuantity < 0) {
            throw new ApiException("Insufficient stock quantity for productId: " + product.getId(), "INSUFFICIENT_STOCK", HttpStatus.CONFLICT);
        }

        //update existing product quantity
        product.setAvailableQuantity(remainingQuantity);
        // productRepository.save(product); // no need to use save as it is part of transactional and hibernate checks for dirty checking

        // if you have too many variables separate this category dto conversion
        Category category = product.getCategory();
        CategoryBasicInfoDto catDto = new CategoryBasicInfoDto(
                category.getId(),
                category.getName()
        );
        return new ProductPurchaseResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                remainingQuantity,
                product.getPrice(),
                catDto
        );
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        try {
            productRepository.deleteById(id);
        } catch (org.springframework.dao.EmptyResultDataAccessException ex) {
            throw new ApiException("Product not found", "PRODUCT_NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ApiException("Product cannot be deleted because it is referenced by other records.", "PRODUCT_DELETE_CONFLICT", HttpStatus.CONFLICT);
        } catch (Exception ex) {
            throw new ApiException("Failed to delete product.", "PRODUCT_DELETE_FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
