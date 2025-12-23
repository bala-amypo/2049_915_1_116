package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ================= REGISTER =================
    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) {

        // ✔ FIX: existsByEmail must exist in repository
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // ✔ FIX: roles must be Set<String>
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of("ROLE_USER"))
                .build();

        user = userRepository.save(user);

        // ✔ FIX: generateToken expects Set<String>
        String token = jwtTokenProvider.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRoles()
        );

        // ✔ FIX: use correct AuthResponse constructor
        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                token,
                String.join(",", user.getRoles())
        );
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // ✔ FIX: orElseThrow must be on Optional<User>
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtTokenProvider.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRoles()
        );

        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                token,
                String.join(",", user.getRoles())
        );
    }
}
