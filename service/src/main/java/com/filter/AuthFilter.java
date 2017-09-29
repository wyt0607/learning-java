package com.filter;

import com.util.JWTUtil;
import io.jsonwebtoken.Claims;
import jdk.nashorn.internal.runtime.regexp.RegExp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Created by WTON on 2017/5/21.
 */
@WebFilter(filterName = "authFilter", urlPatterns = "/*")
public class AuthFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private List whiteList = new ArrayList();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        whiteList.add("/register");
        logger.debug("初始化白名单");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        String contextPath = httpServletRequest.getContextPath();
        whiteList.forEach((e) -> {
            if (e.equals(requestURI)) {
                try {
                    chain.doFilter(request, response);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ServletException e1) {
                    e1.printStackTrace();
                }
            }
        });


        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        String Authorization = null;
        while (headerNames.hasMoreElements()) {
            String s = headerNames.nextElement().toString();
            if (s.equals("authorization")) {
                Authorization = httpServletRequest.getHeader(s);
                try {
                    Claims claims = JWTUtil.parseJWT(Authorization);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            logger.info(s);
        }
        logger.info(Authorization);

        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            Arrays.asList(cookies).forEach((cookie) -> {
                cookie.getName();
                cookie.getValue();
            });
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
