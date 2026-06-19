package com.s.commerce.domain.products.valueObject;

import com.fasterxml.jackson.annotation.JsonValue;
import com.s.commerce.domain.common.exceptions.InvalidArgumentException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record ProductId(
        @JsonValue
        @Column(name = "id")
        UUID value
) implements Serializable {

    public ProductId {
        if (value == null) {
            throw new InvalidArgumentException("ProductId cannot be null");
        }
    }

    public static ProductId newId() {
        return new ProductId(UUID.randomUUID());
    }
}