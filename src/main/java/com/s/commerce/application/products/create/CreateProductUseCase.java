package com.s.commerce.application.products.create;

import com.s.commerce.application.products.mappers.ProductMapper;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.repositories.IProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCase {

  private final IProductRepository productRepository;

  public CreateProductUseCase(IProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public CreateProductResponse execute(CreateProductRequest request) {
      Product product = ProductMapper.toEntity(request);

      Product savedProduct = productRepository.create(product);

      return new CreateProductResponse(savedProduct.getId());
  }
  
}