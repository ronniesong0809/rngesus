package com.ronsong.rngesus.common.exception;

import com.ronsong.rngesus.common.api.ApiResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ApiResult<Map<String, Object>> handle(ApiException exception) {
        if (exception.getErrorCode() != null) {
            return ApiResult.failed(exception.getErrorCode());
        }
        return ApiResult.failed(exception.getMessage());
    }
}
