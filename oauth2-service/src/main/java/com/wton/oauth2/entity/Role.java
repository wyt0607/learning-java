package com.wton.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("role")
@ApiModel(value = "Role对象", description = "")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("角色名")
    @TableField("rolename")
    private String rolename;
    @ApiModelProperty("角色权限标识")
    @TableField("authority")
    private String authority;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private String enabled;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rolename=" + rolename +
                ", authority=" + authority +
                ", enabled=" + enabled +
                "}";
    }
}
