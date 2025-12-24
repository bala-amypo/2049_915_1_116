package com.example.demo.entity;

import java.util.Set;

public class User {
    private Long id;
    private String email;
    private String password;
    private Set<String> roles;

    // No-argument constructor
    public User() {}

    // All-argument constructor
    public User(Long id, String email, String password, Set<String> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    // Builder pattern
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    // Getters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Set<String> getRoles() { return roles; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRoles(Set<String> roles) { this.roles = roles; }

    // Builder class
    public static class UserBuilder {
        private Long id;
        private String email;
        private String password;
        private Set<String> roles;

        public UserBuilder id(Long id) { this.id = id; return this; }
        public UserBuilder email(String email) { this.email = email; return this; }
        public UserBuilder password(String password) { this.password = password; return this; }
        public UserBuilder roles(Set<String> roles) { this.roles = roles; return this; }

        public User build() {
            return new User(id, email, password, roles);
        }
    }
}