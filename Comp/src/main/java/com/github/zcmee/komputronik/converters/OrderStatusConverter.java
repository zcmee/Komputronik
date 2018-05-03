package com.github.zcmee.komputronik.converters;

import com.github.zcmee.komputronik.dictionaries.OrderStatus;

import javax.persistence.AttributeConverter;

public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderStatus orderStatus) {
        return orderStatus.getValue();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer dbData) {
        return OrderStatus.valueOf(dbData);
    }
}
