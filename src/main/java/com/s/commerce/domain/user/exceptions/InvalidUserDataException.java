package com.s.commerce.domain.user.exceptions;

import com.s.commerce.domain.common.exceptions.InvalidDataException;

public class InvalidUserDataException extends InvalidDataException {

    public InvalidUserDataException(String message) {
        super(message);
    }
}
