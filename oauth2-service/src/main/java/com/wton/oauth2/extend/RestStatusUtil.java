package com.wton.oauth2.extend;


/**
 * @author WTON
 */
public class RestStatusUtil {
    public static final String SUCCESS = "0";
    public static final String SUCCESS_DES = "操作成功";
    public static final String FAILURE = "1";
    public static final String FAILURE_DES = "操作失败";

    public static <T> RestStatus<T> instance(String code, T data, String des) {
        return new RestStatus<T>(code, data, des);
    }

    public static <T> RestStatus<T> success(T data, String des) {
        return instance(SUCCESS, data, des);
    }

    public static RestStatus<String> success(String des) {
        return success("", des);
    }

    public static RestStatus<String> success() {
        return success(SUCCESS_DES);
    }

    public static RestStatus<String> failure() {
        return failure(FAILURE_DES);
    }

    public static RestStatus<String> failure(String des) {
        return failure("", des);
    }

    public static <T> RestStatus<T> failure(T data, String des) {
        return instance(FAILURE, data, des);
    }
}
