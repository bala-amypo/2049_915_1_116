package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_name")
    private Set<String> roles = new HashSet<>();

    @Column(nullable = false)
    private Boolean active = true;

    public User() {}

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.roles = new HashSet<>();
        if (role != null) {
            this.roles.add(role);
        }
    }

    
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long id;
        private String email;
        private String password;
        private String role;
        private Set<String> roles = new HashSet<>();
        private Boolean active = true;

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder role(String role) {
            this.role = role;
            if (role != null) {
                this.roles.add(role);
            }
            return this;
        }

        public UserBuilder roles(Set<String> roles) {
            this.roles = roles != null ? new HashSet<>(roles) : new HashSet<>();
            if (!this.roles.isEmpty()) {
                this.role = this.roles.iterator().next();
            }
            return this;
        }

        public UserBuilder active(Boolean active) {
            this.active = active;
            return this;
        }

        public User build() {
            User user = new User();
            user.id = this.id;
            user.email = this.email;
            user.password = this.password;
            user.role = this.role;
            user.roles = this.roles;
            user.active = this.active;
            return user;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { 
        this.role = role;
        if (role != null) {
            if (this.roles == null) {
                this.roles = new HashSet<>();
            }
            this.roles.add(role);
        }
    }

    public Set<String> getRoles() { 
        return roles != null ? roles : new HashSet<>();
    }
    
    public void setRoles(Set<String> roles) {
        this.roles = roles != null ? roles : new HashSet<>();
        if (!this.roles.isEmpty()) {
            this.role = this.roles.iterator().next();
        }
    }
    
    public Set<String> getRolesSet() {
        return getRoles();
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}