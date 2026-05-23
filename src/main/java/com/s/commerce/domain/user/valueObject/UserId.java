package com.s.commerce.domain.user.valueObject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record UserId(
        @Column(name = "id")
        UUID value
) implements Serializable {

    public UserId {
        if (value == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }
    }

    public static UserId newId() {
        return new UserId(UUID.randomUUID());
    }
}
