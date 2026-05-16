package com.s.commerce.infrastructure.persistence.product;

import com.s.commerce.domain.products.valueObject.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

import com.s.commerce.domain.products.entity.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, ProductId>, JpaSpecificationExecutor<Product> {
}