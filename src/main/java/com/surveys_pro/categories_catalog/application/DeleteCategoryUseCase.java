package com.surveys_pro.categories_catalog.application;

import com.surveys_pro.categories_catalog.domain.service.CategoryService;

public class DeleteCategoryUseCase {
    private final CategoryService categoryService;

    public DeleteCategoryUseCase(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void execute(int id) {
        categoryService.deleteCategory(id);
    }
}
