package com.s.commerce.infrastructure.web.order.converters;

import com.s.commerce.domain.order.valueObject.OrderId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringToOrderIdConverter implements Converter<String, OrderId> {

    @Override
    public OrderId convert(String source) {
        return new OrderId(UUID.fromString(source));
    }
}
