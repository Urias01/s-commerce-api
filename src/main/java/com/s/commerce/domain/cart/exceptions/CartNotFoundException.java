package com.s.commerce.domain.cart.exceptions;

import com.s.commerce.domain.common.exceptions.NotFoundException;

public class CartNotFoundException extends NotFoundException {
    public CartNotFoundException() {
        super("Cart not found!");
    }
}
