package com.s.commerce.domain.cart.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.s.commerce.domain.common.exceptions.InvalidArgumentException;
import jakarta.persistence.Column;

import java.util.UUID;

public record CartItemId(@JsonValue @Column(name = "id") UUID value) {

    @JsonCreator
    public CartItemId {
        if (value == null) {
            throw new InvalidArgumentException("CartItemId cannot be null");
        }
    }

    public static CartItemId newId() {
        return new CartItemId(UUID.randomUUID());
    }

}
