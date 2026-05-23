package com.s.commerce.domain.user.valueObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Email(String value) {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    public Email {
        if (!validEmail(value)) {
            throw new IllegalArgumentException("Invalid e-mail");
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
