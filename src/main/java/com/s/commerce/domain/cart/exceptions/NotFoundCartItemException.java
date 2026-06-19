package com.s.commerce.domain.cart.exceptions;

import com.s.commerce.domain.common.exceptions.NotFoundException;

public class NotFoundCartItemException extends NotFoundException {
    public NotFoundCartItemException(String message) {
        super(message);
    }
}
