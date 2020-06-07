package com.wton.oauth2.config;

import com.wton.oauth2.token.MyJwtAccessTokenConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * @author wton
 */
@Configuration
//@EnableResourceServer
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final DataSource dataSource;
    private ClientCredentialsTokenEndpointFilter clientCredentialsTokenEndpointFilter;
    private AuthenticationManager authenticationManager;

    public AuthorizationServerConfiguration(DataSource dataSource, ClientCredentialsTokenEndpointFilter clientCredentialsTokenEndpointFilter, AuthenticationManager authenticationManager) {
        this.dataSource = dataSource;
        this.clientCredentialsTokenEndpointFilter = clientCredentialsTokenEndpointFilter;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        security.addTokenEndpointAuthenticationFilter(clientCredentialsTokenEndpointFilter);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new MyJwtAccessTokenConverter();
        jwtAccessTokenConverter.setVerifierKey("wton");
        jwtAccessTokenConverter.setSigningKey("wton");
        return jwtAccessTokenConverter;
    }

}