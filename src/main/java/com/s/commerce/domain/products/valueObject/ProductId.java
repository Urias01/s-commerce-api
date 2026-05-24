package com.s.commerce.domain.products.valueObject;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record ProductId(
        @JsonValue
        UUID value
) implements Serializable {

    public ProductId {
        if (value == null) {
            throw new IllegalArgumentException("ProductId cannot be null");
        }
    }

    public static ProductId newId() {
        return new ProductId(UUID.randomUUID());
    }
}