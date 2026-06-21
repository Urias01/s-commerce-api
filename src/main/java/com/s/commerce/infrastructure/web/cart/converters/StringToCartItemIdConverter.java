package com.s.commerce.infrastructure.web.cart.converters;

import com.s.commerce.domain.cart.valueObjects.CartItemId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringToCartItemIdConverter implements Converter<String, CartItemId> {
    @Override
    public CartItemId convert(String source) {
        return new CartItemId(UUID.fromString(source));
    }
}