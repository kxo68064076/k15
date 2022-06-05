package com.woniu.k15.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {
    static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE" };
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                //放行哪些原始域
                .allowedOrigins("*")
                //是否发送 Cookie
                .allowCredentials(true)
                //放行哪些请求方式
                .allowedMethods(ORIGINS)
                //放行哪些原始请求头部信息
                .allowedHeaders("*")
                //暴露哪些头部信息
                .exposedHeaders("*")
                .maxAge(3600);
    }
}
