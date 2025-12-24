package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public UserDto getUser(@PathVariable String email) {

        User user = userService.getUserByEmail(email);

        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getRoles()
        );
    }
}
