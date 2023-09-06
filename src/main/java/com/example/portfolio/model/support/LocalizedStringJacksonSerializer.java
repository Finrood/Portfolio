package com.example.portfolio.model.support;

import com.example.portfolio.utils.LocalizedString;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class LocalizedStringJacksonSerializer extends StdSerializer<LocalizedString> {

    public LocalizedStringJacksonSerializer() {
        super(LocalizedString.class);
    }

    @Override
    public void serialize(LocalizedString value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (value.hasAnyTranslation()) {
            jsonGenerator.writeStartObject();
            for (Map.Entry<Locale, String> entry : value.getTranslations().entrySet()) {
                jsonGenerator.writeStringField(entry.getKey().getLanguage(), entry.getValue());
            }
            jsonGenerator.writeEndObject();
        } else {
            jsonGenerator.writeString(value.getFallbackText());
        }
    }
}
