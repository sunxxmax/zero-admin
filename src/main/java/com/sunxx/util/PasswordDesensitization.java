package com.sunxx.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.function.Function;

public class PasswordDesensitization extends JsonSerializer<String> {

    /**
     * 邮箱正则(半匹配)
     */

    Function<String, String> desensitize = (String target) -> "********";


    @Override
    public void serialize(String o, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        String value = desensitize.apply(o);
        jsonGenerator.writeString(value);
    }
}