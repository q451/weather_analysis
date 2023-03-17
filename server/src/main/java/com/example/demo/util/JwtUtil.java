package com.example.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.model.weather.Weather;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Map;

/**
 * jwt封装
 * @author 杨小平
 * @date 2022年10月31日15:35:51
 */
public class JwtUtil {
    /**
     * 生成一个token
     * @param  weather weather
     * @return token
     */
    public static String getToken(Weather weather) {
        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间7天
        instance.add(Calendar.DATE, 365);

        JWTCreator.Builder builder = JWT.create();
        builder.withAudience("yangXiaoPing")
                .withClaim("userid", weather.getDate())
                .withClaim("password", weather.getMin())
                .withClaim("status", weather.getId());

        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256("yangXiaoPing"));
    }

    /**
     * 验证token合法性 成功返回token
     * @param token
     * @return
     */
    public static DecodedJWT verifyToken(String token){
        if(StringUtils.isEmpty(token)){
            System.out.println("token不能为空");
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("yangXiaoPing")).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt;
    }

    /**
     * 解析token，获取token的对象，返回类型是map对象
     * @param token
     * @return
     * @author 杨小平
     */
    public static Map<String, Claim> getUserAccount(String token){
        return JWT.decode(token).getClaims();
    }

    public static void main(String[] args) {
        Weather userAccount = new Weather();
        userAccount.setDate("2002-01-01");
        userAccount.setMin("2℃");
        userAccount.setId(2002L);

        String token = JwtUtil.getToken(userAccount);
        Map<String,Claim> map = JwtUtil.getUserAccount(token);
        System.out.println(token);
        System.out.println(map);
    }
}
