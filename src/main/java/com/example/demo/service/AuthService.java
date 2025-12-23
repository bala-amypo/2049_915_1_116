package com.example.demo.service;

import com.example.demo.dto.RegisterRequestDto;
import com.example.demo.dto.UserDto;

public interface AuthService {

    UserDto register(RegisterRequestDto request);

    String login(String email, String password);
}
