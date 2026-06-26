package com.s.commerce.domain.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends DomainException {
    public InvalidArgumentException(String message) {
        super(message);
    }
}
