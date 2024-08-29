package com.surveys_pro.categories_catalog.application;


import com.surveys_pro.categories_catalog.domain.entity.Category;
import com.surveys_pro.categories_catalog.domain.service.CategoryService;

public class CreateCategoryUseCase {
    private final CategoryService categoryService;

    public CreateCategoryUseCase(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void execute(Category category) {
        categoryService.createCategory(category);
    }
}
