package com.s.commerce.domain.products.repositories;

import com.s.commerce.application.products.list.ListProductQuery;
import com.s.commerce.domain.common.PageResult;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.valueObject.ProductId;

import java.util.Optional;

public interface IProductRepository {

    Optional<Product> findById(ProductId productId);
    PageResult<Product> findAll(ListProductQuery query);

    Product create(Product product);
    Product update(Product product);

}