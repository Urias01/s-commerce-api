package com.s.commerce.application.products.edit;

import com.s.commerce.application.products.mappers.ProductMapper;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.exceptions.ProductNotFoundException;
import com.s.commerce.domain.products.repositories.IProductRepository;
import com.s.commerce.domain.products.valueObject.ProductId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EditProductUseCase {

    private final IProductRepository productRepository;

    public EditProductUseCase(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public EditProductResponse execute(ProductId productId, EditProductRequest request) {
        log.info("Starting processing to edit a product");
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.warn("Product not found. productId={}", productId);
                    return new ProductNotFoundException();
                });

        Product newProduct = ProductMapper.toEdit(product, request);

        newProduct = this.productRepository.update(newProduct);
        log.info("Edit product successful, productId={}", product.getId());
        return new EditProductResponse(newProduct.getId());
    }
}
