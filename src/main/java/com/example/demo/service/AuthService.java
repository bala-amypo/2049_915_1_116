package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.User;

public interface AuthService {

    User register(AuthRequest request);

    User login(AuthRequest request);
}
