package com.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.*;


/**
 * Created by WTON on 2017/5/21.
 */
@WebFilter(filterName = "authFilter", urlPatterns = "/*")
public class AuthFilter implements Filter{

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private List whiteList = new ArrayList();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        whiteList.add("/register");
        logger.debug("初始化白名单");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest =(HttpServletRequest)request;
        String requestURI = httpServletRequest.getRequestURI();
        String contextPath = httpServletRequest.getContextPath();
        whiteList.forEach((e)->{
            if(e.equals(requestURI)){
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
