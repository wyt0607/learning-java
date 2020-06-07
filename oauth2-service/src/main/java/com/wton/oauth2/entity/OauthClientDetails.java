package com.wton.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("oauth_client_details")
@ApiModel(value = "OauthClientDetails对象", description = "")
public class OauthClientDetails {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "客户端ID , 唯一标志", required = true)
    @TableId("client_id")
    private String clientId;
    @ApiModelProperty("资源服务器ID")
    @TableField("resource_ids")
    private String resourceIds;
    @ApiModelProperty(value = "客户端秘钥")
    @TableField("client_secret")
    private String clientSecret;
    @ApiModelProperty(value = "授权范围")
    @TableField("scope")
    private String scope;
    @ApiModelProperty(value = "授权类型")
    @TableField("authorized_grant_types")
    private String authorizedGrantTypes;
    @ApiModelProperty(value = "重定向URL , 授权码模式(authorization_code)必填")
    @TableField("web_server_redirect_uri")
    private String webServerRedirectUri;
    @ApiModelProperty("权限标识 , 用逗号分隔")
    @TableField("authorities")
    private String authorities;
    @ApiModelProperty("access token 的有效性")
    @TableField("access_token_validity")
    private Integer accessTokenValidity;
    @ApiModelProperty("refresh token 的有效性")
    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;
    @ApiModelProperty("附加的额外信息")
    @TableField("additional_information")
    private String additionalInformation;
    @ApiModelProperty("自动批准")
    @TableField("autoapprove")
    private String autoapprove;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
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

    public String getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }

    @Override
    public String toString() {
        return "OauthClientDetails{" +
                "clientId=" + clientId +
                ", resourceIds=" + resourceIds +
                ", clientSecret=" + clientSecret +
                ", scope=" + scope +
                ", authorizedGrantTypes=" + authorizedGrantTypes +
                ", webServerRedirectUri=" + webServerRedirectUri +
                ", authorities=" + authorities +
                ", accessTokenValidity=" + accessTokenValidity +
                ", refreshTokenValidity=" + refreshTokenValidity +
                ", additionalInformation=" + additionalInformation +
                ", autoapprove=" + autoapprove +
                "}";
    }
}
