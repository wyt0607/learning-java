package com.wton.solr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author wton
 */
@SolrDocument(collection = "SOLR_DOC")
public class SolrDoc {
    @Id
    private String id;
    @Field
    private String msg;
    @Field
    private LocalDateTime createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolrDoc solrDoc = (SolrDoc) o;
        return Objects.equals(id, solrDoc.id) &&
                Objects.equals(msg, solrDoc.msg) &&
                Objects.equals(createTime, solrDoc.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, msg, createTime);
    }

    @Override
    public String toString() {
        return "SolrDoc{" +
                "id='" + id + '\'' +
                ", msg='" + msg + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
