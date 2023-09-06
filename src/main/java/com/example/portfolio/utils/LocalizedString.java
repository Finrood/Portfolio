package com.example.portfolio.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class LocalizedString {
    private static final Logger logger = LoggerFactory.getLogger(LocalizedString.class);

    private  Map<Locale, String> translations = new HashMap<>();
    private String fallbackText;

    public Map<Locale, String> getTranslations() {
        return translations;
    }

    public LocalizedString setTranslations(Map<Locale, String> translations) {
        this.translations = translations;
        return this;
    }

    public String getFallbackText() {
        return fallbackText;
    }

    public LocalizedString setFallbackText(String fallbackText) {
        this.fallbackText = fallbackText;
        return this;
    }

    public boolean hasAnyTranslation() {
        return translations.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LocalizedString that = (LocalizedString) o;
        return Objects.equals(translations, that.translations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(translations, fallbackText);
    }

    @Override
    public String toString() {
        return "LocalizedString{" +
                "translations=" + translations +
                '}';
    }
}
