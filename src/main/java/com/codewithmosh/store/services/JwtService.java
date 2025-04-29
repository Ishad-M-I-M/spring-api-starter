package com.codewithmosh.store.services;

import com.codewithmosh.store.config.JwtConfig;
import com.codewithmosh.store.entities.User;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class JwtService {
    private final JwtConfig jwtConfig;

    public Jwt generateAccessToken(User user) {
        final long tokenExpirationTime = jwtConfig.getAccessTokenExpiration();
        return generateToken(user, tokenExpirationTime);
    }

    public Jwt generateRefreshToken(User user) {
        final long tokenExpirationTime = jwtConfig.getRefreshTokenExpiration();
        return generateToken(user, tokenExpirationTime);
    }

    private Jwt generateToken(User user, Long tokenExpirationTime) {
        return new Jwt(
                Jwts.claims()
                        .subject(user.getId().toString())
                        .issuedAt(new Date())
                        .expiration(new Date(System.currentTimeMillis() + tokenExpirationTime * 1000))
                        .add("email", user.getEmail())
                        .add("name", user.getName())
                        .add("role", user.getRole().toString())
                        .build(),
                jwtConfig.getSecretKey()
        );
    }

    public Jwt parse(String token) {
        var claims = Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return new Jwt(claims, jwtConfig.getSecretKey());
    }
}
