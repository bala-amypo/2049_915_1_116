package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.HashSet;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    private SecretKey getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes();
        if (keyBytes.length < 64) {
            // For testing with short keys, pad to 64 bytes (512 bits) for HS512
            byte[] paddedKey = new byte[64];
            System.arraycopy(keyBytes, 0, paddedKey, 0, keyBytes.length);
            return Keys.hmacShaKeyFor(paddedKey);
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }

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

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    @SuppressWarnings("unchecked")
    public Set<String> getRole(String token) {
        Claims claims = getClaims(token);
        Object rolesObj = claims.get("roles");
        if (rolesObj instanceof String) {
            return Set.of(((String) rolesObj).split(","));
        } else if (rolesObj instanceof List) {
            return new HashSet<>((List<String>) rolesObj);
        }
        return new HashSet<>();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}