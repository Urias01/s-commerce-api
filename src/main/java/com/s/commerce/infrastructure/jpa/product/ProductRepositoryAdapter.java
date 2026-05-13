package com.s.commerce.infrastructure.jpa.product;

import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.valueObject.ProductId;
import org.springframework.stereotype.Repository;

import com.s.commerce.domain.products.repositories.IProductRepository;

import java.util.Optional;

@Repository
public class ProductRepositoryAdapter implements IProductRepository {

    private final ProductRepository productRepository;

    public ProductRepositoryAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return this.productRepository.save(product);
    }
}
