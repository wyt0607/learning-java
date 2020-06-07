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
@TableName("clientdetails")
@ApiModel(value="Clientdetails对象", description="")
public class Clientdetails {

    private static final long serialVersionUID = 1L;

    @TableId("appId")
    private String appId;

    @TableField("resourceIds")
    private String resourceIds;

    @TableField("appSecret")
    private String appSecret;

    @TableField("scope")
    private String scope;

    @TableField("grantTypes")
    private String grantTypes;

    @TableField("redirectUrl")
    private String redirectUrl;

    @TableField("authorities")
    private String authorities;

    @TableField("access_token_validity")
    private Integer accessTokenValidity;

    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    @TableField("additionalInformation")
    private String additionalInformation;

    @TableField("autoApproveScopes")
    private String autoApproveScopes;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }
    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(String grantTypes) {
        this.grantTypes = grantTypes;
    }
    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }
    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
    public String getAutoApproveScopes() {
        return autoApproveScopes;
    }

    public void setAutoApproveScopes(String autoApproveScopes) {
        this.autoApproveScopes = autoApproveScopes;
    }

    @Override
    public String toString() {
        return "Clientdetails{" +
            "appId=" + appId +
            ", resourceIds=" + resourceIds +
            ", appSecret=" + appSecret +
            ", scope=" + scope +
            ", grantTypes=" + grantTypes +
            ", redirectUrl=" + redirectUrl +
            ", authorities=" + authorities +
            ", accessTokenValidity=" + accessTokenValidity +
            ", refreshTokenValidity=" + refreshTokenValidity +
            ", additionalInformation=" + additionalInformation +
            ", autoApproveScopes=" + autoApproveScopes +
        "}";
    }
}
