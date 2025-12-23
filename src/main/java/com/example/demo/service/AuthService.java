package com.example.demo.service;

import com.example.demo.dto.AuthResponseDto;

public interface AuthService {
    AuthResponseDto login(String username, String password);
}
