package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "penalty_calculations")
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private DeliveryRecord deliveryRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_id")
    private BreachRule breachRule;

    @Column(nullable = false)
    private double penaltyAmount;

    @Column(nullable = false)
    private LocalDateTime calculatedAt;

    @PrePersist
    public void prePersist() {
        calculatedAt = LocalDateTime.now();
    }

    // getters & setters
}
