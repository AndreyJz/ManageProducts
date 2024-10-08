package com.store.back.infrastructure.controllers;

import com.store.back.application.service.ICategoryService;
import com.store.back.domain.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.listCategory();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()) {
            return ResponseEntity.ok(categoryOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()) {
            category.setId(id);
            return ResponseEntity.ok(categoryService.save(category));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()) {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok(categoryOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/fields")
    public ResponseEntity<?> fields() {
        Map<String, String> fields = categoryService.getFields();
        return ResponseEntity.ok(fields);
    }

}
