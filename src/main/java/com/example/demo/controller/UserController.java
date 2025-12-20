package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(@RequestParam String email,
                         @RequestParam String password) {
        return service.registerUser(email, password);
    }

    @GetMapping
    public User getByEmail(@RequestParam String email) {
        return service.getUserByEmail(email);
    }
}
