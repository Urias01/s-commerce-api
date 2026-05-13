package com.s.commerce.application.products.edit;

import com.s.commerce.application.products.mappers.ProductMapper;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.repositories.IProductRepository;
import com.s.commerce.domain.products.valueObject.ProductId;
import org.springframework.stereotype.Service;

@Service
public class EditProductUseCase {

    private final IProductRepository productRepository;

    public EditProductUseCase(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public EditProductResponse execute(ProductId productId, EditProductRequest request) {

        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product Not Found"));

        Product newProduct = ProductMapper.toEdit(product, request);

        newProduct = this.productRepository.update(newProduct);

        return new EditProductResponse(newProduct.getId());
    }
}
