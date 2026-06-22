package com.s.commerce.infrastructure.web.order.converters;

import com.s.commerce.domain.order.valueObject.OrderItemId;
import org.springframework.core.convert.converter.Converter;

import java.util.UUID;

public class StringToOrderItemIdConverter implements Converter<String, OrderItemId> {

    @Override
    public OrderItemId convert(String source) {
        return new OrderItemId(UUID.fromString(source));
    }
}
