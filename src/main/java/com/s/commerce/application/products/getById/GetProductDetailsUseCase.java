package com.s.commerce.application.products.getById;

import com.s.commerce.application.products.mappers.ProductMapper;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.exceptions.ProductNotFoundException;
import com.s.commerce.domain.products.repositories.IProductRepository;
import com.s.commerce.domain.products.valueObject.ProductId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetProductDetailsUseCase {

    private final IProductRepository productRepository;

    public GetProductDetailsUseCase(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public GetProductDetailsResponse execute(ProductId productId) {
        log.info("Start get a product details. productId={}", productId);
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.warn("Product not found. productId={}", productId);
                    return new ProductNotFoundException();
                });

        log.info("Get a product details successful. productId={}", productId);
        return ProductMapper.toDetailsResponse(product);
    }
}
