package com.s.commerce.domain.products.exceptions;

import com.s.commerce.domain.common.exceptions.NotFoundException;

public class ProductNotFoundException extends NotFoundException {

    public ProductNotFoundException() {
        super("Product not found!");
    }
}
