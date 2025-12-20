package com.example.demo.service;

import com.example.demo.dto.UserDto;
import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    void deactivateUser(Long id);
}
