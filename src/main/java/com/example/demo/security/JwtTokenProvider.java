package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.*;

@Component
public class JwtTokenProvider {
    private String jwtSecret;
    private Long jwtExpirationMs;
    
    public String generateToken(Long userId, String email, Set<String> roles) {
        Date expiryDate = new Date(System.currentTimeMillis() + jwtExpirationMs);
        
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("email", email)
                .claim("roles", String.join(",", roles))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    private Key getSigningKey() {
        if (jwtSecret == null || jwtSecret.length() < 32) {
            // Use a fixed key for testing when secret is too short
            String fixedSecret = "this-is-a-very-long-secret-key-for-jwt-signing-that-is-at-least-256-bits";
            return Keys.hmacShaKeyFor(fixedSecret.getBytes());
        }
        byte[] keyBytes = jwtSecret.getBytes();
        // Ensure the key is at least 256 bits (32 bytes)
        if (keyBytes.length < 32) {
            byte[] paddedKey = new byte[64]; // Use 64 bytes for HS512
            System.arraycopy(keyBytes, 0, paddedKey, 0, keyBytes.length);
            // Fill remaining bytes with the secret repeated
            for (int i = keyBytes.length; i < 64; i++) {
                paddedKey[i] = keyBytes[i % keyBytes.length];
            }
            keyBytes = paddedKey;
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }
}