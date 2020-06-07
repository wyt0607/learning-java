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
@TableName("oauth_code")
@ApiModel(value="OauthCode对象", description="")
public class OauthCode extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("code")
    private String code;

    @TableField("authentication")
    private String authentication;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    @Override
    public String toString() {
        return "OauthCode{" +
            "code=" + code +
            ", authentication=" + authentication +
        "}";
    }
}
