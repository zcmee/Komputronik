package com.github.zcmee.komputronik.converters;

import com.github.zcmee.komputronik.dictionaries.ExceptedService;

import javax.persistence.AttributeConverter;

public class ExceptedServiceConverter implements AttributeConverter<ExceptedService, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ExceptedService exceptedService) {
        return exceptedService.getValue();
    }

    @Override
    public ExceptedService convertToEntityAttribute(Integer dbData) {
        return ExceptedService.valueOf(dbData);
    }

}
