package com.s.commerce.domain.common;

import java.util.List;

public record PageResult<T>(
        List<T> items,
        long totalItems,
        int page,
        int size,
        int totalPages
) {
}