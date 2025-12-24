package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String email;
    private String password;
    
    @ElementCollection
    private Set<String> roles;
    
    public User() {}
    
    public static UserBuilder builder() { return new UserBuilder(); }
    
    public static class UserBuilder {
        private User user = new User();
        
        public UserBuilder id(Long id) { user.id = id; return this; }
        public UserBuilder email(String email) { user.email = email; return this; }
        public UserBuilder password(String password) { user.password = password; return this; }
        public UserBuilder roles(Set<String> roles) { user.roles = roles; return this; }
        
        public User build() { return user; }
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}