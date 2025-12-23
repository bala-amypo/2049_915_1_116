package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

// Simple in-memory repository for testing
@Repository
public class UserRepository {

    private final Map<String, User> users = new HashMap<>();
    private Long idCounter = 1L;

    public User save(User user) {
        user.setId(idCounter++);
        users.put(user.getEmail(), user);
        return user;
    }

    public User findByEmail(String email) {
        return users.get(email);
    }
}
