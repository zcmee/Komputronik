package com.github.zcmee.komputronik.dictionaries;

import com.fasterxml.jackson.annotation.JsonValue;
import org.hibernate.envers.tools.Pair;

import java.util.*;

public enum RecommendationStatus {
    NEW(1798542, "Nowy"),
    ORDER(1798543, "Zamówienie"),
    NO_CONFIRMATION(1808360, "Brak potwierdzenia"),
    ACCEPTED_BY_CLIENT(1807159, "Zaakceptowane przez klienta"),
    REJECTED_BY_CLIENT(1807160, "Odrzucone przez klienta"),
    RESIGNATION(1798544, "Rezygnacja");

    private final int value;
    private final String describe;
    private static final Map<Integer, RecommendationStatus> recommendationStatusMap = new HashMap<>();
    public static final Set<RecommendationStatus> ACTIVE_STATUSES = Collections.unmodifiableSet(EnumSet.of(ORDER, ACCEPTED_BY_CLIENT, RESIGNATION));

    RecommendationStatus(Integer value, String describe) {
        this.value = value;
        this.describe = describe;
    }

    static {
        for (RecommendationStatus recommendationStatus : RecommendationStatus.values()) {
            recommendationStatusMap.put(recommendationStatus.getValue(), recommendationStatus);
        }
    }

    public int getValue() {
        return value;
    }
    public static RecommendationStatus valueOf(Integer value) {
        return recommendationStatusMap.get(value);
    }

    @JsonValue
    public Pair<Integer, String> getDescribeJson() {
        return new Pair<>(value, describe);
    }
}
