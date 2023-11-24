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
 * 邮箱脱敏器 默认只保留域名
 *
 * @author sunxx
 */
public class EmailDesensitization extends JsonSerializer<String> {

    /**
     * 邮箱正则(半匹配)
     */
    private static final Pattern DEFAULT_PATTERN = Pattern.compile("([A-Za-z0-9_\\-.])+@([A-Za-z0-9_\\-.])+\\.([A-Za-z]{2,4})");

    Function<String, String> desensitize = (String target) -> {
        Matcher matcher = DEFAULT_PATTERN.matcher(target);
        while (matcher.find()) {
            String group = matcher.group();
            int l = group.lastIndexOf("@");
            target = target.replace(group, Symbol.getSymbol(l, Symbol.STAR) + group.substring(l));
        }
        return target;
    };


    @Override
    public void serialize(String o, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        String value = desensitize.apply(o);
        jsonGenerator.writeString(value);
    }
}
