package com.s.commerce.infrastructure.jpa.product;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s.commerce.domain.products.entity.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}