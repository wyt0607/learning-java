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
@TableName("resource")
@ApiModel(value = "Resource对象", description = "")
public class Resource extends BaseEntity {

    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "Resource{" +
                "type=" + type +
                ", url=" + url +
                ", authority=" + authority +
                ", enabled=" + enabled +
                "}";
    }
}
