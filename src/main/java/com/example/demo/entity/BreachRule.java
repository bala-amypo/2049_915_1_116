package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal penaltyPerDay;
    private BigDecimal maxPenaltyPercentage;
    private boolean active;
    private boolean defaultRule;

    public BigDecimal getPenaltyPerDay() { return penaltyPerDay; }
    public BigDecimal getMaxPenaltyPercentage() { return maxPenaltyPercentage; }
    public boolean getActive() { return active; }

    public void setActive(boolean active) {
        this.active = active;
    }
}
