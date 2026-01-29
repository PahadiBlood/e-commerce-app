package xyz.manojraw.ecommerce.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import xyz.manojraw.ecommerce.common.exception.ApiException;
import xyz.manojraw.ecommerce.product.model.Category;
import xyz.manojraw.ecommerce.product.repository.CategoryRepository;
import xyz.manojraw.ecommerce.product.service.CategoryService;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ApiException("Category not found", "NOT_FOUND", HttpStatus.NOT_FOUND));
    }
}
