package com.s.commerce.application.products.list;

import com.s.commerce.domain.common.valueObject.Money;
import com.s.commerce.domain.products.valueObject.ProductId;

public record ListProductResponse(
        ProductId id,
        String name,
        String category,
        Money price
) {
}
