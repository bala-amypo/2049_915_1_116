package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.Optional;

public interface UserService {

    /**
     * Register a new user
     * @param user - user entity with email, password, roles
     * @return saved User
     */
    User registerUser(User user);

    /**
     * Get user by email
     * @param email - user's email
     * @return User if found
     */
    User getUserByEmail(String email);

    /**
     * Optional helper: check if user exists by email
     * @param email - user's email
     * @return true if exists
     */
    boolean existsByEmail(String email);
}
