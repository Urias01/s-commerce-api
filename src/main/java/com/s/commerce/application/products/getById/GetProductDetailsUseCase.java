package com.s.commerce.application.products.getById;

import com.s.commerce.application.products.mappers.ProductMapper;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.repositories.IProductRepository;
import com.s.commerce.domain.products.valueObject.ProductId;
import org.springframework.stereotype.Service;

@Service
public class GetProductDetailsUseCase {

    private final IProductRepository productRepository;

    public GetProductDetailsUseCase(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public GetProductDetailsResponse execute(ProductId productId) {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        return ProductMapper.toDetailsResponse(product);
    }
}
