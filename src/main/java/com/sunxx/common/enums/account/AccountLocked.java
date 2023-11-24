package com.sunxx.common.enums.account;

import jakarta.persistence.AttributeConverter;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum AccountLocked {

    UNKNOWN(-1, "未知"),

    LOCKED(1, "已锁定"),

    UNLOCKED(0, "未锁定");


    private final Integer key;
    private final String value;

    AccountLocked(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static class Convert implements AttributeConverter<AccountLocked, Integer> {
        @Override
        public Integer convertToDatabaseColumn(AccountLocked attribute) {
            return Optional.ofNullable(attribute).map(AccountLocked::getKey).orElse(null);

        }

        @Override
        public AccountLocked convertToEntityAttribute(Integer db) {
            return Arrays.stream(AccountLocked.values())
                    .filter(gender -> gender.getKey().equals(db))
                    .findAny()
                    .orElse(AccountLocked.UNKNOWN);
        }
    }
}
