package com.example.demo.security;

import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class JwtTokenProvider {
    
    private String jwtSecret;
    private Long jwtExpirationMs;
    
    public String generateToken(Long userId, String email, Set<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("roles", String.join(",", roles));
        
        // Simple token generation for testing
        String payload = userId + ":" + email + ":" + String.join(",", roles);
        return Base64.getEncoder().encodeToString(payload.getBytes());
    }
    
    public boolean validateToken(String token) {
        try {
            Base64.getDecoder().decode(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Map<String, Object> getClaims(String token) {
        String decoded = new String(Base64.getDecoder().decode(token));
        String[] parts = decoded.split(":");
        
        Map<String, Object> claims = new HashMap<>();
        if (parts.length >= 3) {
            claims.put("userId", parts[0]);
            claims.put("email", parts[1]);
            claims.put("roles", parts[2]);
        }
        return claims;
    }
}