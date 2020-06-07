package com.wton.oauth2.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

public class ResourceDTO extends BaseDTO {

    @ApiModelProperty(value = "菜单按钮:0 , 接口权限:1")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "type为1 时 必填")
    @TableField("url")
    private String url;
    @ApiModelProperty(value = "授权标识")
    @TableField("authority")
    private String authority;
    @ApiModelProperty(value = "启动状态")
    @TableField("enabled")
    private String enabled;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
