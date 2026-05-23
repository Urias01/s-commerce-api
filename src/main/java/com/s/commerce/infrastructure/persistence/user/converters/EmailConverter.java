package com.s.commerce.infrastructure.persistence.user.converters;

import com.s.commerce.domain.user.valueObject.Email;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.value();
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        return new Email(dbData);
    }
}
