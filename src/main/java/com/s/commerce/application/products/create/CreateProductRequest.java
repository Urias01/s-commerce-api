package com.s.commerce.application.products.create;

import com.s.commerce.domain.products.enums.ProductCategory;
import com.s.commerce.domain.common.valueObject.Money;
import jakarta.validation.constraints.NotBlank;

public record CreateProductRequest(@NotBlank String name, String description, Money price, ProductCategory category) {
}
