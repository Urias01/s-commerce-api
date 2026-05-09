package com.s.commerce.application.products.create;

import java.util.UUID;

public record CreateProductResponse(UUID id) {

    public CreateProductResponse(UUID id) {
        this.id = id;
    }
}
