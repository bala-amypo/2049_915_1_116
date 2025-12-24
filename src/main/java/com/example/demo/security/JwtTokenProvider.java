package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenProvider {

    private String jwtSecret = "default-secret";  // will be overridden in tests
    private long jwtExpirationMs = 3600000L;     // 1 hour default

    public String generateToken(Long userId, String email, Set<String> roles) {
        String rolesCsv = String.join(",", roles);
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("email", email)
                .claim("roles", rolesCsv)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    // Setters for tests
    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public void setJwtExpirationMs(long jwtExpirationMs) {
        this.jwtExpirationMs = jwtExpirationMs;
    }
}
