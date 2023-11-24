package com.sunxx.common.enums.account;

import jakarta.persistence.AttributeConverter;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 账号是否可用
 *
 * @author sunxx
 */
@Getter
public enum AccountEnabled {
    UNKNOWN(-1, "未知"),
    ENABLED(1, "启用"),
    DISABLE(0, "禁用");

    private final Integer key;
    private final String message;

    AccountEnabled(Integer key, String message) {
        this.key = key;
        this.message = message;
    }

    /**
     * 用于数据库和枚举实体互转
     */
    public static class Convert implements AttributeConverter<AccountEnabled, Integer> {

        /**
         * 实体转数据库字段
         *
         * @param attribute 枚举
         * @return 数据库字段值
         */
        @Override
        public Integer convertToDatabaseColumn(AccountEnabled attribute) {
            return Optional.ofNullable(attribute).map(AccountEnabled::getKey).orElse(null);
        }

        /**
         * 数据库字段转实体
         *
         * @param db 数据库字段值
         * @return 枚举
         */
        @Override
        public AccountEnabled convertToEntityAttribute(Integer db) {
            return Arrays.stream(AccountEnabled.values())
                    .filter(gender -> gender.getKey().equals(db))
                    .findAny()
                    .orElse(AccountEnabled.UNKNOWN);
        }
    }
}
