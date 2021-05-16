package com.zr.webstore.exception;

import com.zr.webstore.enums.ExceptionCode;

public class WebStoreException extends RuntimeException {
    Integer code;
    String message;

    public WebStoreException(ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
