package com.surveys_pro.categories_catalog.application;

import java.util.List;

import com.surveys_pro.categories_catalog.domain.entity.Category;
import com.surveys_pro.categories_catalog.domain.service.CategoryService;

public class GetAllCategoriesUseCase {
    private final CategoryService categoryService;

    public GetAllCategoriesUseCase(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<Category> execute() {
        return categoryService.getAllCategories();
    }
}
