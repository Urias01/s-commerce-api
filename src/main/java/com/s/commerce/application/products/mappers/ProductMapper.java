package com.s.commerce.application.products.mappers;


import com.s.commerce.application.products.create.CreateProductRequest;
import com.s.commerce.domain.products.entity.Product;

public class ProductMapper {

    public static Product toEntity(CreateProductRequest request) {
        return  new Product(request.name(), request.description(), request.price(), request.category());
    }
}
