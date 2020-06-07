package com.wton.oauth2.extend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

@ApiModel
public class RestStatus<T> {
    @ApiModelProperty(value = "状态码: 1代表成功,0代表失败")
    private String code;
    @ApiModelProperty(value = "数据项")
    private T data;
    @ApiModelProperty(value = "描述信息")
    private String des;
    @ApiModelProperty(value = "扩展返回值")
    private Map<String, Object> ext;

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }

    public RestStatus() {
    }

    public RestStatus(String code, T data, String des) {
        this.code = code;
        this.data = data;
        this.des = des;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
