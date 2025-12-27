package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret:test-secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-ms:3600000}")
    private long jwtExpirationMs;

    /**
     * Generate JWT token with userId, email and roles
     */
    public String generateToken(Long userId, String email, Set<String> roles) {
        String rolesCsv = roles == null
                ? ""
                : roles.stream().collect(Collectors.joining(","));

        return Jwts.builder()
                .claim("userId", userId)
                .claim("email", email)
                .claim("roles", rolesCsv)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Validate JWT token
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Extract claims from token
     */
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Helper methods (optional but useful)
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = getClaims(token);
        Object id = claims.get("userId");
        return id == null ? null : Long.valueOf(String.valueOf(id));
    }

    public String getEmailFromToken(String token) {
        return String.valueOf(getClaims(token).get("email"));
    }

    public Set<String> getRolesFromToken(String token) {
        String roles = String.valueOf(getClaims(token).get("roles"));
        return Set.of(roles.split(","));
    }
}
