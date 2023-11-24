package com.sunxx.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private T body;
    private Integer code;
    private String message;

    public static <T> Response<T> success(T body) {
        return new Response<>(body, null, "success");
    }

    public static <T> Response<T> failure(Integer code, String message) {
        return new Response<>(null, code, message);
    }

    public static <T> Response<T> failure(String message) {
        return new Response<>(null, null, message);
    }
}
