package com.ronsong.rngesus.common.api;

public enum ApiErrorCode implements IErrorCode {

    SUCCESS(200, "OK"),
    FAILED(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    VALIDATE_FAILED(404, "Not Found");

    private final Integer code;
    private final String message;

    ApiErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApiErrorCode{code=" + code + ", message='" + message + "'}";
    }
}
