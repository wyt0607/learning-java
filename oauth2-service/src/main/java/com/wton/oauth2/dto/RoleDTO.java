package com.wton.oauth2.dto;

import io.swagger.annotations.ApiModelProperty;

public class RoleDTO extends BaseDTO {

    @ApiModelProperty("角色名")
    private String rolename;
    @ApiModelProperty("角色权限标识")
    private String authority;
    @ApiModelProperty("启用状态")
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
}
