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
@JsonSerialize(using = PurchaseStatus.EnumSerializer.class)
public enum PurchaseStatus {
    PAID(1, "已付款"),
    SHIPPED(2, "已发货"),
    RECEIVED(3, "已收货"),

    REFUNDED(4, "已退款"),
    FINISHED(5, "交易完成"),

    UNKNOWN(99, "未知"),
    ;

    private final Integer key;
    private final String message;

    /**
     * 用于数据库和枚举实体互转
     */
    public static class Convert implements AttributeConverter<PurchaseStatus, Integer> {

        /**
         * 实体转数据库字段
         *
         * @param attribute 枚举
         * @return 数据库字段值
         */
        @Override
        public Integer convertToDatabaseColumn(PurchaseStatus attribute) {
            return Optional.ofNullable(attribute).map(PurchaseStatus::getKey).orElse(null);
        }

        /**
         * 数据库字段转实体
         *
         * @param db 数据库字段值
         * @return 枚举
         */
        @Override
        public PurchaseStatus convertToEntityAttribute(Integer db) {
            return Arrays.stream(PurchaseStatus.values())
                    .filter(gender -> gender.getKey().equals(db))
                    .findAny()
                    .orElse(PurchaseStatus.UNKNOWN);
        }
    }

    /**
     * 用于序列化为json
     */
    static class EnumSerializer extends JsonSerializer<PurchaseStatus> {

        @Override
        public void serialize(PurchaseStatus status, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, IOException {
            jsonGenerator.writeString(status.message);
        }
    }
}
