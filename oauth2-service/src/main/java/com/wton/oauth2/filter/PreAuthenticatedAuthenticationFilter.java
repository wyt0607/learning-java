package com.wton.oauth2.filter;

import com.wton.oauth2.Oauth2Application;
import com.wton.oauth2.service.IUserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author wton
 */
public class PreAuthenticatedAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Resource
    private IUserService userService;

    public PreAuthenticatedAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/**"));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

     /*   if (checkParams(request)) {
            return null;
        }*/
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        List<GrantedAuthority> grantedAuthorityList = userService.getUserAuthority(username, password);
        PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken("wton", "wton", grantedAuthorityList);
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("admin");
        grantedAuthorityList.add(authority);
        PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails details = new PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails(request,grantedAuthorityList);
        authenticationToken.setDetails(details);
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    private boolean checkParams(ServletRequest request) {
        return Stream.of("username", "password")
                .map(s -> StringUtils.isEmpty(request.getParameter(s)))
                .reduce(true, (s, s2) -> s & s2);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }


}
