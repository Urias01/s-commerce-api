package com.s.commerce.infrastructure.web.cart.converters;

import com.s.commerce.domain.cart.valueObjects.CartId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringToCartIdConverter implements Converter<String, CartId> {
    @Override
    public CartId convert(String source) {
        return new CartId(UUID.fromString(source));
    }
}