package com.wton.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("用户名")
    @TableField("username")
    private String username;
    @ApiModelProperty("密码")
    @TableField("password")
    private String password;
    @ApiModelProperty("禁用状态")
    @TableField("enabled")
    private String enabled;
    @ApiModelProperty("过期状态")
    @TableField("account_non_expired")
    private String accountNonExpired;
    @ApiModelProperty("锁定状态")
    @TableField("account_non_locked")
    private String accountNonLocked;
    @ApiModelProperty("证书状态")
    @TableField("credentials_non_expired")
    private String credentialsNonExpired;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(String accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public String getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(String accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public String getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(String credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public String toString() {
        return "User{" +
                "username=" + username +
                ", password=" + password +
                ", enabled=" + enabled +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                "}";
    }
}
