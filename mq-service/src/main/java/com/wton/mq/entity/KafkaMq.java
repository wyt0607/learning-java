package com.wton.mq.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author wton
 */
public class KafkaMq implements Serializable {

    private String msg;

    private LocalDateTime createTime;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;

    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KafkaMq kafkaMq = (KafkaMq) o;
        return Objects.equals(msg, kafkaMq.msg) &&
                Objects.equals(createTime, kafkaMq.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msg, createTime);
    }

    @Override
    public String toString() {
        return "KafkaMq{" +
                "msg='" + msg + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
