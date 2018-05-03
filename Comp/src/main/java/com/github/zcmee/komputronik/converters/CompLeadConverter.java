package com.github.zcmee.komputronik.converters;

import com.github.zcmee.komputronik.dictionaries.RecommendationStatus;

import javax.persistence.AttributeConverter;

public class CompLeadConverter implements AttributeConverter<RecommendationStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RecommendationStatus attribute) {
        return attribute.getValue();
    }

    @Override
    public RecommendationStatus convertToEntityAttribute(Integer dbData) {
        return RecommendationStatus.valueOf(dbData);
    }

}
