package com.wton.oauth2.extend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ApiModel
public class RestStatus<T> extends RepresentationModel<RestStatus<T>> {

    public static final String SUCCESS = "0";
    public static final String SUCCESS_MSG = "操作成功";
    public static final String FAILURE = "1";
    public static final String FAILURE_MSG = "操作失败";
    public static final String DEFAULT_CONTENT_EMPTY = "";

    @ApiModelProperty(value = "状态码: 1代表成功,0代表失败")
    private String code;
    @ApiModelProperty(value = "数据项")
    private T content;
    @ApiModelProperty(value = "描述信息")
    private String msg;
    @ApiModelProperty(value = "扩展返回值")
    private Map<String, Object> ext;
    /**
     * 仅仅为了 Knife4
     */
    @ApiModelProperty(value = "资源链接描述")
    private Link links;

    public RestStatus() {
    }

    public RestStatus(String code, T content, String msg, Link... links) {
        this.code = code;
        this.content = content;
        this.msg = msg;
        add(links);
    }

    public static <T> RestStatus<T> instance(String code, T content, String msg, Link... links) {
        return new RestStatus<T>(code, content, msg, links);
    }

    public static <T> RestStatus<T> success(T content, String msg, Link... links) {
        return instance(SUCCESS, content, msg, links);
    }

    public static <T> RestStatus<T> success(T content, Link... links) {
        return success(content, SUCCESS_MSG, links);
    }

    public static RestStatus<String> success(String msg, Link... links) {
        return success(DEFAULT_CONTENT_EMPTY, msg, links);
    }

    public static RestStatus<String> success() {
        return success(SUCCESS_MSG);
    }

    /**
     * -----------------------------成功失败分割线-----------------------------
     */

    public static RestStatus<String> failure() {
        return failure(FAILURE_MSG);
    }

    public static <T> RestStatus<T> failure(T content, Link... links) {
        return failure(content, FAILURE_MSG, links);
    }

    public static RestStatus<String> failure(String msg, Link... links) {
        return failure(DEFAULT_CONTENT_EMPTY, msg, links);
    }

    public static <T> RestStatus<T> failure(T content, String msg, Link... links) {
        return instance(FAILURE, content, msg, links);
    }

    public Map<String, Object> getExt() {
        if (Objects.isNull(ext)) {
            ext = new HashMap<>();
        }
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
