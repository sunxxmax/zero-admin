package com.sunxx.jackson.desensitization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.sunxx.util.DesensitizedUtils;
import lombok.NoArgsConstructor;
import org.hibernate.service.spi.ServiceException;

import java.io.IOException;
import java.util.Objects;

/**
 * 自定义脱敏序列化类
 *
 * @author masily
 * @since 2021-6-9 14:43:52
 */
@NoArgsConstructor
public class DesensitizationSerialize extends JsonSerializer<Object> implements ContextualSerializer {

    private DesensitizationType type;


    public DesensitizationSerialize(final DesensitizationType type) {
        this.type = type;
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty beanProperty) throws JsonMappingException {
        if (Objects.nonNull(beanProperty)) {
            //获取字段是否有脱敏注解，有则创建一个序列化对象，并调用serialize方法
            Desensitization desensitization = beanProperty.getAnnotation(Desensitization.class);
            // 如果定义了脱敏注解，就将需要脱敏的类型传入DesensitizationSerialize构造函数
            if (Objects.nonNull(desensitization)) {
                return new DesensitizationSerialize(desensitization.value());
            }
            return provider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return provider.findNullValueSerializer(null);
    }

    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        switch (this.type) {
            case USER_ID -> jsonGenerator.writeNumber(DesensitizedUtils.userId());
            case CHINESE_NAME -> jsonGenerator.writeString(DesensitizedUtils.chineseName(String.valueOf(value)));
            case ID_CARD -> jsonGenerator.writeString(DesensitizedUtils.idCardNum(String.valueOf(value), 3, 3));
            case FIXED_PHONE -> jsonGenerator.writeString(DesensitizedUtils.fixedPhone(String.valueOf(value)));
            case MOBILE_PHONE -> jsonGenerator.writeString(DesensitizedUtils.mobilePhone(String.valueOf(value)));
            case ADDRESS -> jsonGenerator.writeString(DesensitizedUtils.address(String.valueOf(value), 8));
            case EMAIL -> jsonGenerator.writeString(DesensitizedUtils.email(String.valueOf(value)));
            case PASSWORD -> jsonGenerator.writeString(DesensitizedUtils.password(String.valueOf(value)));
            default -> throw new ServiceException("不存在脱敏类型");
        }
    }
}
