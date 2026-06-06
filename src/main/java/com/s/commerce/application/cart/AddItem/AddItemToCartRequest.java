package com.s.commerce.application.cart.AddItem;

import com.s.commerce.application.cart.common.CartItemRequest;
import com.s.commerce.domain.user.valueObject.UserId;

public record AddItemToCartRequest(CartItemRequest item, UserId customerId) {
}
