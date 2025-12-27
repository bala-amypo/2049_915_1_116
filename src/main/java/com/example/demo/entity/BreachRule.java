package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "breach_rules")
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    @Column(nullable = false)
    private double penaltyRate;

    // getters & setters
}
