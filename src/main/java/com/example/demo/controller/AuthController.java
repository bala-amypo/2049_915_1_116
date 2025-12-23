package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // Constructor Injection (as required)
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request) {

        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request) {

        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
