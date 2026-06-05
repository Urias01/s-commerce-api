package com.s.commerce.domain.cart.valueObjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;

import java.util.UUID;

public record CartItemId(@JsonValue @Column(name = "id") UUID value) {

    public CartItemId {
        if (value == null) {
            throw new IllegalArgumentException("CartItemId cannot be null");
        }
    }

    public static CartItemId newId() {
        return new CartItemId(UUID.randomUUID());
    }

}
