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
@JsonSerialize(using = PartnerStatus.EnumSerializer.class)
public enum PartnerStatus {
    ENABLED(1, "启用"),
    DISABLE(0, "禁用"),
    UNKNOWN(99, "未知"),

    ;

    private final Integer key;
    private final String message;

    public static class Convert implements AttributeConverter<PartnerStatus, Integer> {
        /**
         * 实体转数据库字段
         *
         * @param attribute 枚举
         * @return 数据库字段值
         */
        @Override
        public Integer convertToDatabaseColumn(PartnerStatus attribute) {
            return Optional.ofNullable(attribute).map(PartnerStatus::getKey).orElse(null);
        }

        /**
         * 数据库字段转实体
         *
         * @param db 数据库字段值
         * @return 枚举
         */
        @Override
        public PartnerStatus convertToEntityAttribute(Integer db) {
            return Arrays.stream(PartnerStatus.values())
                    .filter(gender -> gender.getKey().equals(db))
                    .findAny()
                    .orElse(PartnerStatus.UNKNOWN);
        }
    }

    /**
     * 用于序列化为json
     */
    static class EnumSerializer extends JsonSerializer<PartnerStatus> {

        @Override
        public void serialize(PartnerStatus status, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, IOException {
            jsonGenerator.writeString(status.message);
        }
    }
}
