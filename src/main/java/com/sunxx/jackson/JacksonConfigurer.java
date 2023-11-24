package com.sunxx.jackson;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * https://github.com/FasterXML/jackson-modules-java8
 *
 * @author masily
 * @since 2021-5-25 15:11:47
 */
@Configuration
public class JacksonConfigurer {
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * <code> JavaTimeModule jdk8Module = new JavaTimeModule();
     * <p>
     * // with 3.0 (or with 2.10 as alternative)
     * <p>
     * // or different mapper for other format
     * <p>
     * ObjectMapper objectMapper = JsonMapper.builder()
     * <p>
     * .addModule(new ParameterNamesModule())
     * <p>
     * .addModule(jdk8Module)
     * <p>
     * .addModule(new JavaTimeModule())
     * <p>
     * // and possibly other configuration, modules, then:
     * <p>
     * .build();
     * </code>
     *
     * @return Jackson2ObjectMapperBuilderCustomizer
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {

        return builder -> {
            builder.locale(Locale.CHINA);
            builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
            builder.simpleDateFormat(FORMAT);
            JavaTimeModule jdk8Module = new JavaTimeModule();
            jdk8Module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            jdk8Module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            jdk8Module.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
            jdk8Module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            jdk8Module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            jdk8Module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

            builder.modules(jdk8Module);
        };
    }


    // @Bean
    // @Primary
    // public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
    //     ObjectMapper objectMapper = builder.createXmlMapper(false).build();
    //     // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
    //     // Include.Include.ALWAYS 默认
    //     // Include.NON_DEFAULT 属性为默认值不序列化
    //     // Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的
    //     // Include.NON_NULL 属性为NULL 不序列化
    //     objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    //     // 允许出现单引号
    //     objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    //
    //     // null值的处理，必须要放在第一步，否则会将下面的日期格式化覆盖掉
    //     objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<>() {
    //         @Override
    //         public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    //             gen.writeString("");
    //         }
    //     });
    //     return objectMapper;
    // }
}
