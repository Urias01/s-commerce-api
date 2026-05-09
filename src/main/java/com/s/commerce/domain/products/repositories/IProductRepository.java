package com.s.commerce.domain.products.repositories;

import com.s.commerce.domain.products.entity.Product;

public interface IProductRepository {

    Product create(Product product);

}