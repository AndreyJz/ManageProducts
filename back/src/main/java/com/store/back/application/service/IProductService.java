package com.store.back.application.service;

import com.store.back.domain.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> listProduct();
    Optional<Product> findProductById(Long id);
    Product save(Product product);
    Optional<Product> updateProduct(Long id, Product product);
    Optional<Product> deleteProduct(Long id);

}
