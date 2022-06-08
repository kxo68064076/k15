package com.woniu.k15.intercepters;

import com.woniu.k15.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermissionIntercepter implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        //查看以下  客户请求的资源
        String path = request.getServletPath();
        System.out.println("========="+path+"=========");

        //跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if ("OPTIONS".equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            return true ;
        }


        String token = request.getHeader("token");
        if (token==null){
            response.getOutputStream().print(0);
            return false;
        }else {
            try {
                JwtUtil.parseToken(token);
                return true ;
            }catch (ExpiredJwtException e1){
                e1.printStackTrace();
                response.getOutputStream().print(1); //token过期
            }catch (SignatureException e2){
                e2.printStackTrace();
                response.getOutputStream().print(2); //非法token
            }catch (RuntimeException e3){
                e3.printStackTrace();
                response.getOutputStream().print(3); //系统错误
            }
        }
        return true;
    }
}
