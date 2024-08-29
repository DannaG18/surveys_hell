package com.surveys_pro.categories_catalog.application;

import java.util.Optional;

import com.surveys_pro.categories_catalog.domain.entity.Category;
import com.surveys_pro.categories_catalog.domain.service.CategoryService;

public class FindCategoryUseCase {
    private final CategoryService categoryService;

    public FindCategoryUseCase(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Optional<Category> execute(int id) {
        return categoryService.findCategory(id);
    }
}
