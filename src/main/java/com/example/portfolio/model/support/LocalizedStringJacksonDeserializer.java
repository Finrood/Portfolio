package com.example.portfolio.model.support;

import com.example.portfolio.utils.LocalizedString;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class LocalizedStringJacksonDeserializer extends StdDeserializer<LocalizedString> {

    public LocalizedStringJacksonDeserializer() {
        super(LocalizedString.class);
    }

    /**
     * Implements the behavior for creating a LocalizedString from different input types:
     *
     * <ul>
     *     <li>{@code null}: Returns a {@code null} LocalizedString.</li>
     *     <li>Text value: Returns a LocalizedString with the fallback text initialized to the value of the attribute.
     *         Normally, this scenario is unexpected since we expect a localized string to be represented in its structured form
     *         when edited, but it's supported anyway.</li>
     *     <li>Map value: Returns a LocalizedString initialized with the provided translations. The fallback text is intentionally lost
     *         as long as at least one translation exists, as it is no longer valuable. If no translation exists, it falls back
     *         to either scenario #1 or #2.</li>
     * </ul>
     */
    @Override
    public LocalizedString deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        if (node.isNull()) {
            return null;
        } else if (node.isTextual()) {
            return new LocalizedString().setFallbackText(node.asText());
        } else {
            final String fallbackText;
            final Map<Locale, String> translations = new HashMap<>();

            final Iterator<Map.Entry<String, JsonNode>> iterator = node.fields();
            while (iterator.hasNext()) {
                final Map.Entry<String, JsonNode> entry = iterator.next();

                if (!entry.getValue().isNull()) {
                    translations.put(Locale.forLanguageTag(entry.getKey()), entry.getValue().asText());
                }
            }

            if (translations.isEmpty()) {
                fallbackText = iterator.next().getValue().asText();
            } else {
                fallbackText = null;
            }

            if (fallbackText == null && translations.isEmpty()) {
                return null;
            } else {
                return new LocalizedString()
                        .setTranslations(translations)
                        .setFallbackText(fallbackText);
            }
        }
    }
}
