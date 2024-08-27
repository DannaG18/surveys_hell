package com.surveys_pro.categories_catalog.application;

import com.surveys_pro.categories_catalog.domain.entity.Category;
import com.surveys_pro.categories_catalog.domain.service.CategoryService;

public class UpdateCategoryUseCase {
    private final CategoryService categoryService;

    public UpdateCategoryUseCase(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void execute(Category category) {
        categoryService.updateCategoty(category);
    }
}
