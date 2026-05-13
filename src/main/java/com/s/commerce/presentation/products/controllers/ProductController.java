package com.s.commerce.presentation.products.controllers;

import com.s.commerce.application.commons.response.ApiResponse;
import com.s.commerce.application.products.create.CreateProductRequest;
import com.s.commerce.application.products.create.CreateProductResponse;
import com.s.commerce.application.products.create.CreateProductUseCase;
import com.s.commerce.application.products.edit.EditProductRequest;
import com.s.commerce.application.products.edit.EditProductResponse;
import com.s.commerce.application.products.edit.EditProductUseCase;
import com.s.commerce.domain.products.valueObject.ProductId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final EditProductUseCase editProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, EditProductUseCase editProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.editProductUseCase = editProductUseCase;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateProductResponse>> createProduct(@RequestBody CreateProductRequest request) {
        CreateProductResponse response = this.createProductUseCase.execute(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @PutMapping(":productId")
    public ResponseEntity<ApiResponse<EditProductResponse>> editProduct(@PathVariable ProductId productId, @RequestBody EditProductRequest request) {
        EditProductResponse response = this.editProductUseCase.execute(productId, request);

        return ResponseEntity.ok().body(ApiResponse.success(response));
    }
}
