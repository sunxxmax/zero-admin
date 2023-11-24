package com.sunxx.common.enums;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sunxx.common.enums.account.AccountEnabled;
import jakarta.persistence.AttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * 删除
 *
 * @author sunxx
 * @since 2021/10/25 9:31
 */
@Getter
@AllArgsConstructor
@JsonSerialize(using = Deleted.EnumSerializer.class)
public enum Deleted {

    NO(0, "未删除"),
    YES(1, "已删除"),
    UNKNOWN(99, "未知");


    private final Integer key;

    private final String message;

    /**
     * 用于数据库和枚举实体互转
     */
    public static class Convert implements AttributeConverter<Deleted, Integer> {
        /**
         * 实体转数据库字段
         *
         * @param attribute 枚举
         * @return 数据库字段值
         */
        @Override
        public Integer convertToDatabaseColumn(Deleted attribute) {
            return Optional.ofNullable(attribute).map(Deleted::getKey).orElse(null);
        }

        /**
         * 数据库字段转实体
         *
         * @param db 数据库字段值
         * @return 枚举
         */
        @Override
        public Deleted convertToEntityAttribute(Integer db) {
            // 将数字转换为描述
            return Arrays.stream(values())
                    .filter(entity -> entity.getKey().equals(db))
                    .findAny()
                    .orElse(Deleted.UNKNOWN);
        }
    }

    /**
     * 用于序列化为json
     */
    static class EnumSerializer extends JsonSerializer<Deleted> {

        @Override
        public void serialize(Deleted deleted, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, IOException {
            jsonGenerator.writeString(deleted.message);
        }
    }
}
