package com.wton.oauth2.extend;

import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * @author WTON
 */
public class JwtUtil {

    private final static long NO_TIMEOUT = -1;

    private static String stringKey ="wton";

    /**
     * 注入私钥
     *
     * @param stringKey 私钥
     */
    public void setStringKey(String stringKey) {
       setKey(stringKey);
    }

    private static void setKey(String stringKey) {
        JwtUtil.stringKey = stringKey;
    }

    /**
     * 由字符串生成加密key
     *
     * @return SecretKey
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 创建jwt
     *
     * @param id        uuid
     * @param subject   用户基本信息,json格式
     * @param ttlMillis 过期时间
     * @return jwt
     */
    public static String createJwt(String id, String subject, long ttlMillis) throws JwtException {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 创建jwt
     *
     * @param id      uuid
     * @param subject 用户基本信息,json格式
     * @return jwt  NO_TIMEOUT
     */
    public static String createJwt(String id, String subject) throws JwtException {
        return createJwt(id, subject, NO_TIMEOUT);
    }

    /**
     * 解密jwt
     *
     * @param jwt 需要解析的jwt
     * @return Claims 加密前的信息
     */
    public static Claims parseJwt(String jwt) throws JwtException {
        SecretKey key = generalKey();
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
    }

    /**
     * @param jwt       token
     * @param ttlMillis 增加的时间 毫秒
     * @return jwt
     */
    public static String refreshJwt(String jwt, long ttlMillis) {
        long millis = System.currentTimeMillis();
        Claims claims = parseJwt(jwt);
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(millis + ttlMillis));
        return builder.compact();
    }

    /**
     * @param jwt 需要延长超期时间的token 默认7天
     * @return jwt
     */
    public static String refreshJwt(String jwt) {
        return refreshJwt(jwt, 1000 * 60 * 60 * 24 * 7);
    }

}
