package com.s.commerce.application.cart.removeItem;

import com.s.commerce.domain.cart.valueObjects.CartItemId;
import com.s.commerce.domain.user.valueObject.UserId;

public record RemoveItemFromCartRequest(CartItemId itemId, UserId customerId) {
}
