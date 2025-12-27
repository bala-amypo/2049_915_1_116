package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.RegisterRequestDto;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserAccountRepository userAccountRepository, 
                          PasswordEncoder passwordEncoder, 
                          JwtTokenProvider jwtTokenProvider) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {
        if (userAccountRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("User with email exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : "ROLE_USER");
        user.setActive(true);

        user = userAccountRepository.save(user);

        String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail(), Set.of(user.getRole()));

        return new AuthResponseDto(token, user.getId(), user.getEmail(), user.getRole());
    }

    @Override
    public AuthResponseDto login(AuthRequestDto request) {
        User user = userAccountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        if (!user.getActive()) {
            throw new BadRequestException("Account is inactive");
        }

        String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail(), Set.of(user.getRole()));

        return new AuthResponseDto(token, user.getId(), user.getEmail(), user.getRole());
    }
}