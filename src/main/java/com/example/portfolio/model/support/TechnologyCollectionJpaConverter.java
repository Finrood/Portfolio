package com.example.portfolio.model.support;

import com.example.portfolio.model.Technology;
import jakarta.persistence.AttributeConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class TechnologyCollectionJpaConverter implements AttributeConverter<Collection<Technology>, String> {
    public TechnologyCollectionJpaConverter() {
    }

    @Override
    public String convertToDatabaseColumn(Collection<Technology> attribute) {
        if (CollectionUtils.isEmpty(attribute)) {
            return null;
        }
        return attribute.stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public Collection<Technology> convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return new ArrayList<>();
        }
        return Arrays.stream(dbData.split(","))
                .map(Technology::valueOf)
                .collect(Collectors.toList());
    }
}
