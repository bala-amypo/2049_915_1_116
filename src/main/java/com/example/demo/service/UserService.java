package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    /**
     * Registers a new user.
     *
     * @param user User entity with username, password, roles
     * @return saved User entity
     */
    User registerUser(User user);

    /**
     * Fetch user by ID.
     *
     * @param id user ID
     * @return User entity
     */
    User getUserById(Long id);

    /**
     * Fetch user by username.
     *
     * @param username username/email
     * @return User entity
     */
    User getUserByUsername(String username);

    /**
     * Fetch all users.
     *
     * @return list of users
     */
    List<User> getAllUsers();

    /**
     * Delete a user by ID.
     *
     * @param id user ID
     */
    void deleteUser(Long id);
}
