package com.s.commerce.application.products.create;

import com.s.commerce.domain.products.enums.ProductCategory;
import com.s.commerce.domain.products.valueObject.Money;

public record CreateProductRequest(String name, String description, Money price, ProductCategory category) {
}
