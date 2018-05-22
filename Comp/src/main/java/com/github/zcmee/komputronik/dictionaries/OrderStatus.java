package com.github.zcmee.komputronik.dictionaries;

import com.fasterxml.jackson.annotation.JsonValue;
import org.hibernate.envers.tools.Pair;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
    NEW(1798552, "Nowy"),
    REALIZATION(1798545, "Realizacja"),
    CANCELLATION(1798546, "Anulacja");

    private final int value;
    private final String describe;
    private static final Map<Integer, OrderStatus> orderStatusMap = new HashMap<>();

    OrderStatus(Integer value, String describe) {
        this.value = value;
        this.describe = describe;
    }

    static {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            orderStatusMap.put(orderStatus.getValue(), orderStatus);
        }
    }

    public int getValue() {
        return value;
    }
    public static OrderStatus valueOf(Integer value) {
        return orderStatusMap.get(value);
    }

    @JsonValue
    public Pair<Integer, String> getDescribeJson() {
        return new Pair<>(value, describe);
    }
}
