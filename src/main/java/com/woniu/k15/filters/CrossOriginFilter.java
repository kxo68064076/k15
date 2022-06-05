package com.woniu.k15.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CrossOriginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        //请求头 是可以随时添加 更多的
        res.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN,token");
        /*
            每一个ajax异步请求 ，都会请求两次 第一次请求 都是 options请求 不需要程序员处理
         */
        //  OPTIONS 请求  每一个ajax请求时，都会首先发送一个OPTIONS 请求 这个请求 不需要程序员处理
        if (((HttpServletRequest) servletRequest).getMethod().equals("OPTIONS")) {
            servletResponse.getWriter().println("ok");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
