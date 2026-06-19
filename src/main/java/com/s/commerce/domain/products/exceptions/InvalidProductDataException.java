package com.s.commerce.domain.products.exceptions;

import com.s.commerce.domain.common.exceptions.InvalidDataException;

public class InvalidProductDataException extends InvalidDataException {
    public InvalidProductDataException(String message) {
        super(message);
    }
}
