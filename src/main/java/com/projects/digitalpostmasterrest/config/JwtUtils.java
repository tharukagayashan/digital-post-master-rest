package com.projects.digitalpostmasterrest.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.projects.digitalpostmasterrest.error.ErrorAlert;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtils {
    private static final String SECRET = "sach@98";
    private static final long EXPIRATION_TIME = 86400000;
    private static final String ISSUER = "Token Provider";

    public static String generateToken(String username) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        String token = JWT.create()
                .withIssuer(ISSUER)
                .withExpiresAt(expirationDate)
                .withSubject(username)
                .sign(algorithm);

        return token;
    }

    public static Boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();

            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public static String getUsernameFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject();
    }

    public static boolean isTokenExpired(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("your-issuer")
                    .build();
            verifier.verify(token);
            return false;
        } catch (JWTVerificationException e) {
            return true;
        }
    }

}