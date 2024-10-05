package com.store.back.infrastructure.repositories.category;

import com.store.back.application.service.ICategoryService;
import com.store.back.domain.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Category> listCategory() {
        return (List<Category>)categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Transactional
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public Optional<Category> updateCategory(Long id, Category category) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category categoryToUpdate = categoryRepository.save(category);
            categoryToUpdate.setDescription(category.getDescription());
            categoryToUpdate.setStatus(category.getStatus());
            return Optional.of(categoryRepository.save(categoryToUpdate));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Category> deleteCategory(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            categoryRepository.delete(categoryOptional.get());
        }
        return Optional.empty();
    }
}
