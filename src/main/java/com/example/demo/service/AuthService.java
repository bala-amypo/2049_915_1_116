package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;

public interface AuthService {

    /**
     * Registers a new user and returns JWT token.
     *
     * @param request AuthRequest containing email and password
     * @return AuthResponse containing JWT token
     */
    AuthResponse register(AuthRequest request);

    /**
     * Authenticates a user and returns JWT token.
     *
     * @param request AuthRequest containing email and password
     * @return AuthResponse containing JWT token
     */
    AuthResponse login(AuthRequest request);

    /**
     * Loads a user by email.
     *
     * @param email user's email
     * @return User entity
     */
    User getUserByEmail(String email);
}
