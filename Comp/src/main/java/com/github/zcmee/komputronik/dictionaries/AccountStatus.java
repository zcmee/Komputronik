package com.github.zcmee.komputronik.dictionaries;

import java.util.HashMap;
import java.util.Map;

public enum AccountStatus {
    ACTIVE_ACCOUNT(1480910);

    private final int value;
    private static final Map<Integer, AccountStatus> accountStatusMap = new HashMap<>();

    AccountStatus(int value) {
        this.value = value;
    }

    static {
        for (AccountStatus accountStatus : AccountStatus.values()) {
            accountStatusMap.put(accountStatus.getValue(), accountStatus);
        }
    }

    public int getValue() {
        return value;
    }
    public static AccountStatus valueOf(Integer value) {
        return accountStatusMap.get(value);
    }

}
