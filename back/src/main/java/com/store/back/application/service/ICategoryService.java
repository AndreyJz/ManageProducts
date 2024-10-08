package com.store.back.application.service;

import com.store.back.domain.entities.Category;

import java.util.Map;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> listCategory();
    Optional<Category> findCategoryById(Long id);
    Category save(Category category);
    Optional<Category> updateCategory(Long id, Category category);
    Optional<Category> deleteCategory(Long id);
    Map<String, String> getFields();
}
