package com.s.commerce.application.products.list;

import com.s.commerce.application.commons.request.BasePagination;
import com.s.commerce.domain.products.enums.ProductCategory;

public record ListProductQuery(
        String name,
        ProductCategory category,
        BasePagination pagination) {
}
