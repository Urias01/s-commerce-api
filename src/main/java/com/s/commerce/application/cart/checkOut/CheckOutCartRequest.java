package com.s.commerce.application.cart.checkOut;

import com.s.commerce.domain.cart.valueObjects.CartItemId;
import com.s.commerce.domain.user.valueObject.UserId;

import java.time.LocalDateTime;
import java.util.List;

public record CheckOutCartRequest(List<CartItemId> cartItemIdList, LocalDateTime scheduledDeliveryDate, UserId customerId) {
}
