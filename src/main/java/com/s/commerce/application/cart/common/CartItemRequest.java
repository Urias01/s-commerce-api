package com.s.commerce.application.cart.common;

import com.s.commerce.domain.products.valueObject.ProductId;

public record CartItemRequest(int quantity, ProductId productId) {
}
