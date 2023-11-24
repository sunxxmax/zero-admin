package com.sunxx.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sunxx.jackson.desensitization.Symbol;

import java.io.IOException;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号脱敏
 *
 * @author sunxx
 */
public class PhoneDesensitization extends JsonSerializer<String> {
    /**
     * 手机号正则
     */
    private static final Pattern DEFAULT_PATTERN = Pattern.compile("(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}");

    /**
     * 手机号脱敏 只保留前3位和后4位
     */
    Function<String, String> desensitize = (target) -> {
        Matcher matcher = DEFAULT_PATTERN.matcher(target);
        while (matcher.find()) {
            String group = matcher.group();
            target = target.replace(group, group.substring(0, 3) + Symbol.getSymbol(4, Symbol.STAR) + group.substring(7, 11));
        }
        return target;
    };


    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        String value = desensitize.apply(s);
        jsonGenerator.writeString(value);
    }
}
