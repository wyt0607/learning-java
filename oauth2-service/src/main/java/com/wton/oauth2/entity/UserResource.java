package com.wton.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.wton.oauth2.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author wton
 * @since 2020-04-04
 */
@TableName("user_resource")
@ApiModel(value="UserResource对象", description="")
public class UserResource  {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private String userId;

    @TableId("resource_id")
    private String resourceId;

    public UserResource(String userId, String resourceId) {
        this.userId = userId;
        this.resourceId = resourceId;
    }

    public UserResource() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "UserResource{" +
            "userId=" + userId +
            ", resourceId=" + resourceId +
        "}";
    }
}
