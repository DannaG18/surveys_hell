package com.surveys_pro.dto.application;

import com.surveys_pro.categories_catalog.domain.service.CategoryService;
import com.surveys_pro.dto.CategoryDto;

import java.util.List;

public class FindCategoryDtoByIdUC {
    private CategoryService categoryService;

    public FindCategoryDtoByIdUC(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<CategoryDto> execute(int id){
        return categoryService.findCategoryDto(id);
    }
}