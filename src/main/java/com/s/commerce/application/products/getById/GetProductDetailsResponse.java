package com.s.commerce.application.products.getById;

import com.s.commerce.domain.products.enums.ProductCategory;
import com.s.commerce.domain.products.valueObject.Money;
import com.s.commerce.domain.products.valueObject.ProductId;

public record GetProductDetailsResponse(
        ProductId id,
        String name,
        String description,
        ProductCategory category,
        Money price
) {
}
