package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "breach_rules")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal penaltyPerDay;

    /**
     * IMPORTANT:
     * Wrapper Double (NOT primitive double)
     * ✔ allows null checks
     * ✔ fixes == null / != null compiler errors
     */
    @Column(nullable = false)
    private Double maxPenaltyPercentage;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean isDefaultRule;
}
