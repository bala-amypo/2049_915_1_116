package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Set;

@Component
public class JwtTokenProvider {

    // These will be injected in tests using reflection
    private String jwtSecret = "default-secret-key-default-secret-key";
    private long jwtExpirationMs = 3600000;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // ---------------- GENERATE TOKEN ----------------
    public String generateToken(Long userId, String email, Set<String> roles) {

        String rolesCsv = String.join(",", roles);

        return Jwts.builder()
                .claim("userId", userId)
                .claim("email", email)
                .claim("roles", rolesCsv)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ---------------- VALIDATE TOKEN ----------------
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    // ---------------- GET CLAIMS ----------------
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
