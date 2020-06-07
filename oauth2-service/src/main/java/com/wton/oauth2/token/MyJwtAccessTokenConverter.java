package com.wton.oauth2.token;

import com.wton.oauth2.service.IUserResourceService;
import com.wton.oauth2.service.IUserService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;

public class MyJwtAccessTokenConverter  extends JwtAccessTokenConverter {

    @Resource
    private IUserService  userService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {



        return super.enhance(accessToken, authentication);
    }
}
