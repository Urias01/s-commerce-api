package com.s.commerce.domain.cart.exceptions;

import com.s.commerce.domain.common.exceptions.NotFoundException;

public class CartItemNotFoundException extends NotFoundException {
    public CartItemNotFoundException() {
        super("Cart item not found");
    }
}
