package com.surveys_hell.categories_catalog.domain.service;

import java.util.List;
import java.util.Optional;

import com.surveys_hell.categories_catalog.domain.entity.Category;

public interface CategoryService {
    void createCategory (Category category);
    Optional<Category> findCategory (int id);
    void updateCategoty (Category category);
    void deleteCategory (int id);
    List<Category> getAllCategories();
}
