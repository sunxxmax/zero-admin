package com.sunxx.common;

public record PageHelper(
        Integer page,
        Integer size
) {

    public PageHelper() {
        this(0, 10);
    }

}
