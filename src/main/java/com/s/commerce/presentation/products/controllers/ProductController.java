package com.s.commerce.presentation.products.controllers;

import com.s.commerce.application.commons.response.ApiResponse;
import com.s.commerce.application.products.create.CreateProductRequest;
import com.s.commerce.application.products.create.CreateProductResponse;
import com.s.commerce.application.products.create.CreateProductUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateProductResponse>> createProduct(@RequestBody CreateProductRequest request) {
        CreateProductResponse response = this.createProductUseCase.execute(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));

    }
}
