package com.example.demo.dto;

public class AuthRequest {
    private String email;
    private String password;

    // No-argument constructor
    public AuthRequest() {}

    // All-argument constructor
    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Setters
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}