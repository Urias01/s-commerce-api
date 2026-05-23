package com.s.commerce.infrastructure.persistence.user.converters;

import com.s.commerce.domain.user.valueObject.HashedPassword;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class HashedPasswordConverter implements AttributeConverter<HashedPassword, String> {
    @Override
    public String convertToDatabaseColumn(HashedPassword attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.value();
    }

    @Override
    public HashedPassword convertToEntityAttribute(String dbData) {
        return new HashedPassword(dbData);
    }
}
