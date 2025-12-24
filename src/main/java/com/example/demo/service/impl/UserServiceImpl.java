package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto getUserById(Long id) {
        // Dummy data
        return new UserDto(id, "Jeevika");
    }
}
