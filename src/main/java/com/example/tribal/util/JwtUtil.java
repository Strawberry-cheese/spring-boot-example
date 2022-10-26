package com.example.tribal.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;

/**
 * @author lhui    919238538@qq.com:
 * @description
 * @date 2022/7/29 下午3:49
 */


public class JwtUtil {
    // 过期时间为3分钟
    private static final long EXPIRE_TIME = 3 * 60 * 1000;

    // 秘钥
    private static final String TOKEN_SECRET = "5f9b8c38c38c66da7ff1a16ea687edcc";

    /**
     * 创建token
     *
     * @param userId
     * @param username
     * @return
     */
    public static String sign(long userId, String username) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        // 验证 token
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
        } catch (IllegalArgumentException e) {
            return false;
        } catch (JWTVerificationException e) {
            return false;
        }

        return true;

    }

}
