package com.s.commerce.application.commons.response;

import java.util.List;

public record PageResponse<T>(
        List<T> items,
        long totalItems,
        int page,
        int size,
        int totalPages
) {
}