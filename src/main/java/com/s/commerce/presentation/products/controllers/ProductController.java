package com.s.commerce.presentation.products.controllers;

import com.s.commerce.application.commons.request.BasePagination;
import com.s.commerce.application.commons.response.ApiResponse;
import com.s.commerce.application.commons.response.PageResponse;
import com.s.commerce.application.products.create.CreateProductRequest;
import com.s.commerce.application.products.create.CreateProductResponse;
import com.s.commerce.application.products.create.CreateProductUseCase;
import com.s.commerce.application.products.edit.EditProductRequest;
import com.s.commerce.application.products.edit.EditProductResponse;
import com.s.commerce.application.products.edit.EditProductUseCase;
import com.s.commerce.application.products.list.ListProductQuery;
import com.s.commerce.application.products.list.ListProductResponse;
import com.s.commerce.application.products.list.ListProductUseCase;
import com.s.commerce.domain.products.enums.ProductCategory;
import com.s.commerce.domain.products.valueObject.ProductId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final EditProductUseCase editProductUseCase;
    private final ListProductUseCase listProductUseCase;

    public ProductController(
            CreateProductUseCase createProductUseCase,
            EditProductUseCase editProductUseCase,
            ListProductUseCase listProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.editProductUseCase = editProductUseCase;
        this.listProductUseCase = listProductUseCase;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CreateProductResponse>> createProduct(@RequestBody @Valid CreateProductRequest request) {
        CreateProductResponse response = this.createProductUseCase.execute(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<EditProductResponse>> editProduct(@PathVariable ProductId productId, @RequestBody EditProductRequest request) {
        EditProductResponse response = this.editProductUseCase.execute(productId, request);

        return ResponseEntity.ok().body(ApiResponse.success(response));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ListProductResponse>> listProduct(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "name") String field,
            @RequestParam(defaultValue = "asc") String direction) {

        var query = new ListProductQuery(
                name,
                category,
                new BasePagination(page, size, field, direction)
        );

        var response = listProductUseCase.execute(query);

        return ResponseEntity.ok(response);
    }
}
