package com.s.commerce.domain.cart.valueObjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;

import java.util.UUID;

public record CartId(@JsonValue @Column(name = "id") UUID value) {

    public CartId {
        if (value == null) {
            throw new IllegalArgumentException("CartId cannot be null");
        }
    }

    public static CartId newId() {
        return new CartId(UUID.randomUUID());
    }

}

