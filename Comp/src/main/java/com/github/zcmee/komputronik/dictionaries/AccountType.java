package com.github.zcmee.komputronik.dictionaries;

import java.util.HashMap;
import java.util.Map;

public enum AccountType {
    COMP_INSTALATOR(1806273),
    COMP_OPL(1806274);

    private final int value;
    private static final Map<Integer, AccountType> accountTypeMap = new HashMap<>();

    AccountType(int value) {
        this.value = value;
    }

    static {
        for (AccountType accountType : AccountType.values()) {
            accountTypeMap.put(accountType.getValue(), accountType);
        }
    }

    public int getValue() {
        return value;
    }
    public static AccountType valueOf(Integer value) {
        return accountTypeMap.get(value);
    }
}
