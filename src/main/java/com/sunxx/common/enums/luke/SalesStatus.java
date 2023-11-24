package com.sunxx.common.enums.luke;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.AttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
@JsonSerialize(using = SalesStatus.EnumSerializer.class)
public enum SalesStatus {

    PAYING(1, "待回款"),

    PAYBACK(2, "预回款"),

    FINISHED(3, "已完成"),

    UNKNOWN(99, "未知"),

    ;

    private final Integer key;
    private final String message;

    public static class Convert implements AttributeConverter<SalesStatus, Integer> {
        /**
         * 实体转数据库字段
         *
         * @param attribute 枚举
         * @return 数据库字段值
         */
        @Override
        public Integer convertToDatabaseColumn(SalesStatus attribute) {
            return Optional.ofNullable(attribute).map(SalesStatus::getKey).orElse(null);
        }

        /**
         * 数据库字段转实体
         *
         * @param db 数据库字段值
         * @return 枚举
         */
        @Override
        public SalesStatus convertToEntityAttribute(Integer db) {
            return Arrays.stream(SalesStatus.values())
                    .filter(gender -> gender.getKey().equals(db))
                    .findAny()
                    .orElse(SalesStatus.UNKNOWN);
        }
    }

    /**
     * 用于序列化为json
     */
    static class EnumSerializer extends JsonSerializer<SalesStatus> {

        @Override
        public void serialize(SalesStatus status, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, IOException {
            jsonGenerator.writeString(status.message);
        }
    }
}
