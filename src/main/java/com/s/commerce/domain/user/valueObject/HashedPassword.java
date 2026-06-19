package com.s.commerce.domain.user.valueObject;

import com.s.commerce.domain.common.exceptions.InvalidArgumentException;
import com.s.commerce.domain.user.exceptions.InvalidUserDataException;

public record HashedPassword(String value) {

    public HashedPassword {
        if (value == null || value.isBlank()) {
            throw new InvalidUserDataException("Invalid hashed password");
        }
    }
}
