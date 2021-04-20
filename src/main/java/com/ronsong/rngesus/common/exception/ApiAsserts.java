package com.ronsong.rngesus.common.exception;

import com.ronsong.rngesus.common.api.ErrorCode;

public class ApiAsserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(ErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}