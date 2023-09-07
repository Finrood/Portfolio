package com.example.portfolio.model.support;

import com.example.portfolio.utils.LocalizedString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.hibernate.type.SerializationException;

import java.io.IOException;

@Converter(autoApply = true)
public class LocalizedStringJpaConverter implements AttributeConverter<LocalizedString, String> {
    private final ObjectMapper objectMapper;

    public LocalizedStringJpaConverter() {
        objectMapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule();
        module.addSerializer(LocalizedString.class, new LocalizedStringJacksonSerializer());
        module.addDeserializer(LocalizedString.class, new LocalizedStringJacksonDeserializer());
        objectMapper.registerModule(module);
    }

    @Override
    public String convertToDatabaseColumn(LocalizedString attribute) throws SerializationException {
        try {
            return (attribute == null) ? null : (attribute.hasAnyTranslation() ? objectMapper.writeValueAsString(attribute) : attribute.getFallbackText());
        } catch (JsonProcessingException e) {
            throw new SerializationException("Unable to serialize localized string " + attribute, e);
        }
    }

    @Override
    public LocalizedString convertToEntityAttribute(String dbData) throws SerializationException {
        try {
            if (dbData == null) {
                return null;
            } else if (isValidJson(dbData.trim())) {
                return objectMapper.readValue(dbData, LocalizedString.class);
            } else {
                return new LocalizedString().setFallbackText(dbData);
            }
        } catch (IOException e) {
            throw new SerializationException("Unable to deserialize localized string " + dbData, e);
        }
    }

    private boolean isValidJson(String jsonString) {
        try {
            final JsonNode jsonNode = objectMapper.readTree(jsonString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
