package com.s.commerce.application.products.create;

import com.s.commerce.application.products.mappers.ProductMapper;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.repositories.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateProductUseCase {

  private final IProductRepository productRepository;

  public CreateProductUseCase(IProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public CreateProductResponse execute(CreateProductRequest request) {
      log.info("Starting processing to create a product");
      Product product = ProductMapper.toEntity(request);

      Product savedProduct = productRepository.create(product);

      log.info("Create product Successful, productId={}", product.getId());
      return new CreateProductResponse(savedProduct.getId());
  }
  
}