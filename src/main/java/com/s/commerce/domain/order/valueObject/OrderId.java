package com.s.commerce.domain.order.valueObject;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record OrderId(@JsonValue @Column(name = "id") UUID value) {

    public OrderId {
        if (value == null) {
            throw new IllegalArgumentException("OrderId cannot be null");
        }
    }

    public static OrderId newId() {
        return new OrderId(UUID.randomUUID());
    }

}
