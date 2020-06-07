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
@TableName("role_resource")
@ApiModel(value="RoleResource对象", description="")
public class RoleResource {

    private static final long serialVersionUID = 1L;

    @TableId("role_id")
    private String roleId;

    @TableId("resource_id")
    private String resourceId;

    public RoleResource() {
    }

    public RoleResource(String roleId, String resourceId) {
        this.roleId = roleId;
        this.resourceId = resourceId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "RoleResource{" +
            "roleId=" + roleId +
            ", resourceId=" + resourceId +
        "}";
    }
}
