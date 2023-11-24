package com.sunxx.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
public class JwtUtils {

    private static final String ISSUER = "sunxx.com";
    private static final String SECRET_KEY = "your-secret-key";
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hour
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public static String generate(String username) {

        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 1 hour from now
                .sign(ALGORITHM);
    }

    public static void validateToken(String token, BiConsumer<Boolean, DecodedJWT> biConsumer) {
        try {
            DecodedJWT decodedJWT = verify(token);
            biConsumer.accept(true, decodedJWT);
        } catch (Exception e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
            biConsumer.accept(false, null);
        }
    }

    public static DecodedJWT verify(String token) {

        JWTVerifier verifier = JWT
                .require(ALGORITHM)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }

}
