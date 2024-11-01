package com.adera.take.home.test.exception;

public class CustomHandledException extends Exception {
    private String code;

    public CustomHandledException(String code, String message) {
        super(message);
        this.setCode(code);
    }

    public CustomHandledException(String code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
