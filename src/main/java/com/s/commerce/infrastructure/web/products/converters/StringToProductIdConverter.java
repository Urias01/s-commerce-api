package com.s.commerce.infrastructure.web.products.converters;

import com.s.commerce.domain.products.valueObject.ProductId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class StringToProductIdConverter implements Converter<String, ProductId> {


    @Override
    public ProductId convert(String source) {
        return new ProductId(UUID.fromString(source));
    }
}
