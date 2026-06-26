package com.s.commerce.domain.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
public class InvalidOperationException extends DomainException {
    public InvalidOperationException(String message) {
        super(message);
    }
}
