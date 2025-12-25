package com.example.demo.dto;

import lombok.Data;
import java.util.Set;

@Data
public class UserDto {
    private String email;
    private String password;
    private Set<String> roles;
}