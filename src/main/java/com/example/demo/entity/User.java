package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    @Column(nullable = false)
    private Boolean active = true;

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.roles = new HashSet<>();
        if (role != null) {
            this.roles.add(role);
        }
    }

    public Set<String> getRoles() {
        if (roles != null && !roles.isEmpty()) {
            return roles;
        }
        if (role != null) {
            return Set.of(role);
        }
        return new HashSet<>();
    }
}