package com.store.back.infrastructure.repositories.product;

import com.store.back.domain.entities.Category;
import com.store.back.domain.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
