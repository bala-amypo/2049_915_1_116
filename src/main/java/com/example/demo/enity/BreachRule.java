package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "breach_rules")
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer minDelayDays;

    private Integer maxDelayDays;

    private Double penaltyPercentage;

    public BreachRule() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinDelayDays() {
        return minDelayDays;
    }

    public void setMinDelayDays(Integer minDelayDays) {
        this.minDelayDays = minDelayDays;
    }

    public Integer getMaxDelayDays() {
        return maxDelayDays;
    }

    public void setMaxDelayDays(Integer maxDelayDays) {
        this.maxDelayDays = maxDelayDays;
    }

    public Double getPenaltyPercentage() {
        return penaltyPercentage;
    }

    public void setPenaltyPercentage(Double penaltyPercentage) {
        this.penaltyPercentage = penaltyPercentage;
    }
}
