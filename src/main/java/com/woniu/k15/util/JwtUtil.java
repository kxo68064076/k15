package com.woniu.k15.util;


import com.woniu.k15.vo.JwtInfo;
import io.jsonwebtoken.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class JwtUtil {
    private static Properties properties = new Properties();


    static {
        InputStream in = JwtUtil.class.getClassLoader().getResourceAsStream("jwt.properties");
        //把信息 加载到集合中
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成token
     * @param jwtInfo   自定义信息的对象
     * @return
     */
    public static String createToken(JwtInfo jwtInfo){
        try {
            //利用建造者 设计模式
            JwtBuilder jwtBuilder = Jwts.builder();
            //设置头信息
            jwtBuilder.setHeaderParam("typ",properties.getProperty("typ"));
            jwtBuilder.setHeaderParam("alg",properties.getProperty("alg"));

            //设置载荷
            jwtBuilder.setIssuer(properties.getProperty("issuer"));
            long expire_time = 30*60*1000 ; //30分钟的毫秒数
            String expiration = properties.getProperty("expiration");
            jwtBuilder.setExpiration(new Date(new Date().getTime() + Long.parseLong(expiration)));
            jwtBuilder.setSubject(properties.getProperty("suject"));

            //设置自定义信息
            jwtBuilder.claim(properties.getProperty("userName"),jwtInfo.getUsername());

            //设置签证
            jwtBuilder.signWith(SignatureAlgorithm.HS256,properties.getProperty("secret"));

            //生成 token
            String token = jwtBuilder.compact();
            return token ;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw  new RuntimeException("生成Token出错!");
        }
    }

    /**
     * 解析JWT的token
     * @param token
     * @return
     */
    public static Claims parseToken(String token){
        try {
            JwtParser jwtParser = Jwts.parser();
            Jws<Claims> claimsJws = jwtParser.setSigningKey(properties.getProperty("secret")).parseClaimsJws(token);
            Claims body = claimsJws.getBody();

            return body;
        }catch (ExpiredJwtException e){
            e.printStackTrace();
            throw  new ExpiredJwtException(null,null,"token已经过期！",e);
        }catch (SignatureException e1){
            e1.printStackTrace();
            throw new SignatureException("密钥不正确！",e1);
        }catch (RuntimeException e2){
            e2.printStackTrace();
            throw new RuntimeException("解析token出错！",e2);
        }
    }


    /**
     * 解析JWT的token  返回里面的用户名
     * @param token
     * @return
     */
    public static String getUsername(String token){
        try {
            JwtParser jwtParser = Jwts.parser();
            Jws<Claims> claimsJws = jwtParser.setSigningKey(properties.getProperty("secret")).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            String userName = (String) body.get(properties.getProperty("userName"));
            return userName;
        }catch (ExpiredJwtException e){
            e.printStackTrace();
            throw  new ExpiredJwtException(null,null,"token已经过期！",e);
        }catch (SignatureException e1){
            e1.printStackTrace();
            throw new SignatureException("密钥不正确！",e1);
        }catch (RuntimeException e2){
            e2.printStackTrace();
            throw new RuntimeException("解析token出错！",e2);
        }
    }

}
