package com.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by WTON on 2017/5/16.
 */
@WebFilter(filterName = "crossFilter", urlPatterns = "/*")
public class CrossFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(CrossFilter.class);
    private String[] whiteList = {
            "http://localhost:8888",
            "https://wton.vip",
            "http://wton.vip"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("初始化跨域过滤器");
        logger.debug(whiteList.toString());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest res = (HttpServletRequest) request;
        String origin = res.getHeader("Origin");
        boolean containsFlag = Arrays.asList(whiteList).contains(origin);
        if (containsFlag) {
            resp.addHeader("Access-Control-Allow-Origin", origin);
            resp.addHeader("Access-Control-Allow-Headers",
                    "Content-Type");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
