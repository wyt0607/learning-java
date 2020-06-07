package com.wton.oauth2.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wton.oauth2.entity.OauthClientDetails;
import com.wton.oauth2.service.IOauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private IOauthClientDetailsService clientDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (!supports(authentication.getClass())) {
            return null;
        }

        Object clientId = authentication.getPrincipal();
        Object clientSecret = authentication.getCredentials();

        QueryWrapper<OauthClientDetails> queryWrapper = Wrappers.query();
        queryWrapper.eq("client_id", clientId);
        queryWrapper.eq("client_secret", clientSecret);
        OauthClientDetails oauthClientDetails = clientDetailsService.getOne(queryWrapper);
        if (Objects.nonNull(oauthClientDetails)) {
            String[] authorities = oauthClientDetails.getAuthorities().split(",");
            List<SimpleGrantedAuthority> authorityList = Stream.of(authorities).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            return new UsernamePasswordAuthenticationToken(clientId, clientSecret, authorityList);
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
