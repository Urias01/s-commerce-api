package com.s.commerce.presentation.cart.requests;

import com.s.commerce.domain.cart.valueObjects.CartItemId;

import java.time.LocalDateTime;
import java.util.List;

public record CheckoutCartHttpRequest(List<CartItemId> cartItemIdList, LocalDateTime scheduledDeliveryDate) {
}
