package me.webapp.common.util.jwt;

/*-
 * ========================LICENSE_START=================================
 * springmvc
 * %%
 * Copyright (C) 2018 Wei Qian
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =========================LICENSE_END==================================
 */

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import me.webapp.common.util.security.RSAUtil;
import me.webapp.config.AppConfig;
import me.webapp.domain.User;
import org.apache.commons.lang.time.DateUtils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class JwtUtil {

    /**
     * JWT加解密算法
     */
    private Algorithm algorithm;


    private JwtUtil() {
        try {
            PrivateKey privateKey = RSAUtil.loadPrivateKeyFromPfx(
                Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt-rsa.pfx"),
                "123456"
            );
            PublicKey publicKey = RSAUtil.loadPublicKeyFromCer(
                Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt-rsa.cer"));
            this.algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey);
        } catch (Exception e) {
            throw new SecurityException("无法初始化JWT组件");
        }
    }

    private static volatile JwtUtil instance;
    public static JwtUtil getInstance() {
        if (instance == null) {
            synchronized (JWT.class) {
                if (instance == null) {
                    instance = new JwtUtil();
                }
            }
        }
        return instance;
    }


    public JwtUtil(PublicKey publicKey, PrivateKey privateKey) {
        this.algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey);
    }



    /**
     * 创建JWT token，默认当天过期
     * @return
     */
    public String newToken(User user) {
        JWTCreator.Builder builder = JWT.create()
            .withIssuer(ISSUER)
            .withSubject(SUBJECT);
        return builder
            .withExpiresAt(DateUtils.addDays(new Date(), AppConfig.JWT_EXPIRE_DURATION_AS_DAY))
            .withIssuedAt(new Date())
            .withJWTId(String.valueOf(UUID.randomUUID()))
            .withAudience(user.getUsername())
            .sign(algorithm);
    }


    /**
     * 验证前端带回的token是否有效
     *
     * @param token
     * @return
     */
    public boolean validate(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * 获取JWT token中的用户名
     * @param token
     * @return
     */
    public String decodeUserName(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);

        decodedJWT.getHeader();
        decodedJWT.getPayload();
        decodedJWT.getSignature();
        decodedJWT.getToken();
        decodedJWT.getExpiresAt();
        decodedJWT.getIssuer();

        return decodedJWT.getAudience().get(0);
    }




    private static final String ISSUER = "NewHorizonLtd";
    private static final String SUBJECT = "Admin";


    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setUsername("qianwei");
        user.setPassword("88863650qw");
        JwtUtil.getInstance().newToken(user);
    }

}
