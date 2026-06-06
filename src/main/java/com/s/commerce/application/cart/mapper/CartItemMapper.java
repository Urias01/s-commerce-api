package com.s.commerce.application.cart.mapper;

import com.s.commerce.application.cart.common.CartItemRequest;
import com.s.commerce.domain.cart.entities.Cart;
import com.s.commerce.domain.cart.entities.CartItems;
import com.s.commerce.domain.products.entity.Product;

public class CartItemMapper {

    public static CartItems toEntity(CartItemRequest item, Cart cart, Product product) {
        return new CartItems(item.quantity(), product, cart);
    }
}
