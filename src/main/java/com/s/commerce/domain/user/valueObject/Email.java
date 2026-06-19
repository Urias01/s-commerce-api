package com.s.commerce.domain.user.valueObject;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.s.commerce.domain.user.exceptions.InvalidUserDataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public record Email(@JsonValue String value) {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    @JsonCreator
    public Email {
        if (!validEmail(value)) {
            throw new InvalidUserDataException("Invalid e-mail");
        }

        value = value.trim().toLowerCase();
    }

    private static boolean validEmail(String value) {

        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        Matcher matches = PATTERN.matcher(value);

        return matches.matches();
    }


}
