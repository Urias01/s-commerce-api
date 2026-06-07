package com.s.commerce.application.cart.removeItem;

import com.s.commerce.domain.cart.valueObjects.CartId;
import com.s.commerce.domain.common.valueObject.Money;

public record RemoveItemFromCartResponse(CartId id, Money total) {
}
