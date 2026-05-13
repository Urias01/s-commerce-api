package com.s.commerce.domain.products.repositories;

import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.valueObject.ProductId;

import java.util.Optional;
import java.util.UUID;

public interface IProductRepository {

    Optional<Product> findById(ProductId productId);

    Product create(Product product);
    Product update(Product product);

}