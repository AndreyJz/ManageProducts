package com.store.back.infrastructure.repositories.category;

import com.store.back.domain.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
