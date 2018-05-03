package com.github.zcmee.komputronik.dictionaries;

import com.fasterxml.jackson.annotation.JsonValue;
import org.hibernate.envers.tools.Pair;

import java.util.HashMap;
import java.util.Map;

public enum ExceptedService {
    MOBILE(1798539, "Mobile"),
    PAID_SERVICE(1798540, "Fix - Bezpłatny"),
    FREE_SERVICE(1798541, "Fix - Instalacja płatna");

    private final int value;
    private final String describe;
    private static final Map<Integer, ExceptedService> exceptedServiceMap = new HashMap<>();

    ExceptedService(Integer value, String describe) {
        this.value = value;
        this.describe = describe;
    }

    static {
        for (ExceptedService exceptedService : ExceptedService.values()) {
            exceptedServiceMap.put(exceptedService.getValue(), exceptedService);
        }
    }

    public int getValue() {
        return value;
    }
    public static ExceptedService valueOf(Integer value) {
        return exceptedServiceMap.get(value);
    }

    @JsonValue
    public Pair<Integer, String> getDescribeJson() {
        return new Pair<>(value, describe);
    }

}
