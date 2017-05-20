package com.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;


/**
 * Created by WTON on 2017/5/21.
 */
@WebFilter(filterName = "authFilter", urlPatterns = "/*")
public class AuthFilter implements Filter{

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest =(HttpServletRequest)request;
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String s = headerNames.nextElement().toString();
            logger.debug(s);
        }
        Cookie[] cookies = httpServletRequest.getCookies();
        Arrays.asList(cookies).forEach((cookie)->{
                cookie.getName();
                cookie.getValue();
        });
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
