package com.wton.oauth2.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseControllerAdvice{


    @ExceptionHandler(Exception.class)
    public String baseExceptionHandler(Exception e) {
        e.printStackTrace();
        return "系统错误";
    }

}
