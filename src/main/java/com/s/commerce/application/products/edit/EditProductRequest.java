package com.s.commerce.application.products.edit;

import com.s.commerce.domain.products.enums.ProductCategory;
import com.s.commerce.domain.products.valueObject.Money;

public record EditProductRequest(String name, String description, Money price, ProductCategory category) {
}
