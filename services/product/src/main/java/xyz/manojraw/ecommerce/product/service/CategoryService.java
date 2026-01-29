package xyz.manojraw.ecommerce.product.service;

import xyz.manojraw.ecommerce.product.model.Category;

public interface CategoryService {
    // if you have big app, separate this in a different category interface
    //CategoryDomainService   → returns entities
    //CategoryQueryService    → returns DTOs

    Category getById(Long aLong);
}
