package com.s.commerce.infrastructure.jpa.product;

import com.s.commerce.domain.products.entity.Product;
import org.springframework.stereotype.Repository;

import com.s.commerce.domain.products.repositories.IProductRepository;

@Repository
public class ProductRepositoryAdapter implements IProductRepository {

    private final ProductRepository productRepository;

    public ProductRepositoryAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return this.productRepository.save(product);
    }
}
