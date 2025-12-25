package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String generateToken(Long userId, String email) {
        return "dummy-jwt-token-" + email;
    }
}
