package com.sunxx.common.enums;

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
@JsonSerialize(using = Fixed.EnumSerializer.class)
public enum Fixed {

    NO(0, "非固定"),
    YES(1, "固定"),
    UNKNOWN(99, "未知"),

    ;


    private final Integer key;

    private final String message;

    /**
     * 用于数据库和枚举实体互转
     */
    public static class Convert implements AttributeConverter<Fixed, Integer> {
        /**
         * 实体转数据库字段
         *
         * @param attribute 枚举
         * @return 数据库字段值
         */
        @Override
        public Integer convertToDatabaseColumn(Fixed attribute) {
            return Optional.ofNullable(attribute).map(Fixed::getKey).orElse(null);
        }

        /**
         * 数据库字段转实体
         *
         * @param db 数据库字段值
         * @return 枚举
         */
        @Override
        public Fixed convertToEntityAttribute(Integer db) {
            // 将数字转换为描述
            return Arrays.stream(values())
                    .filter(entity -> entity.getKey().equals(db))
                    .findAny()
                    .orElse(Fixed.UNKNOWN);
        }
    }

    /**
     * 用于序列化为json
     */
    static class EnumSerializer extends JsonSerializer<Fixed> {

        @Override
        public void serialize(Fixed fixed, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, IOException {
            jsonGenerator.writeString(fixed.message);
        }
    }
}
