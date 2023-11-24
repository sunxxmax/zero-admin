package com.sunxx.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author masily
 * @since 2020-11-11 16:59:30
 */
@Setter
@Getter
public class BizException extends RuntimeException {

    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable cause, String message) {
        super(message, cause);
    }
}
