package com.wton.oauth2.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Collections;
import java.util.List;

public class PageDTO<T> {

    /**
     * 查询数据列表
     */
    @ApiModelProperty("查询数据列表")
    private List<T> records = Collections.emptyList();

    /**
     * 总数
     */
    @ApiModelProperty("总数")
    private long total;
    /**
     * 每页显示条数
     */
    @ApiModelProperty("每页显示条数")
    private long size;

    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    private long current;


    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }
}
