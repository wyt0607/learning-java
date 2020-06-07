package com.wton.oauth2.dto;

import io.swagger.annotations.ApiModelProperty;

public abstract class BaseDTO {

    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty(hidden = true)
    private Integer current;
    @ApiModelProperty(hidden = true)
    private Integer size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
