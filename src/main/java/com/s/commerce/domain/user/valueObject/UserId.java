package com.s.commerce.domain.user.valueObject;

import com.fasterxml.jackson.annotation.JsonValue;
import com.s.commerce.domain.common.exceptions.InvalidArgumentException;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record UserId(
        @JsonValue
        UUID value
) implements Serializable {

    public UserId {
        if (value == null) {
            throw new InvalidArgumentException("UserId cannot be null");
        }
    }

    public static UserId newId() {
        return new UserId(UUID.randomUUID());
    }
}
