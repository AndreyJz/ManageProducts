package com.store.back.infrastructure.repositories.product;

import com.store.back.application.service.IProductService;
import com.store.back.domain.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> listProduct() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product productToUpdate = productRepository.save(product);
            productToUpdate.setName(product.getName());
            productToUpdate.setCategories((product.getCategories()));
            productToUpdate.setBarCode(product.getBarCode());
            productToUpdate.setStock(product.getStock());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setStatus(product.getStatus());
            productToUpdate.setImage(product.getImage());
            return Optional.of(productRepository.save(productToUpdate));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Product> deleteProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.delete(productOptional.get());
        }
        return Optional.empty();
    }
}
