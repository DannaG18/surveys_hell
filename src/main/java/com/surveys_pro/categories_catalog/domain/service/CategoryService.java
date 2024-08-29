package com.surveys_pro.categories_catalog.domain.service;

import java.util.List;
import java.util.Optional;

import com.surveys_pro.categories_catalog.domain.entity.Category;
import com.surveys_pro.dto.CategoryDto;

public interface CategoryService {
    void createCategory (Category category);
    Optional<Category> findCategory (int id);
    void updateCategoty (Category category);
    void deleteCategory (int id);
    List<Category> getAllCategories();
    List<CategoryDto> findCategoryDto(int id);
}
