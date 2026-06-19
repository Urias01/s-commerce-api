package com.s.commerce.domain.order.valueObject;

import com.fasterxml.jackson.annotation.JsonValue;
import com.s.commerce.domain.common.exceptions.InvalidArgumentException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record OrderItemId(@JsonValue @Column(name = "id") UUID value) {

    public OrderItemId {
        if (value == null) {
            throw new InvalidArgumentException("OrderItemId cannot be null");
        }
    }

    public static OrderItemId newId() {
        return new OrderItemId(UUID.randomUUID());
    }

}
