package com.s.commerce.domain.user.valueObject;

public record HashedPassword(String value) {

    public HashedPassword {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Invalid hashed password");
        }
    }
}
