package com.example.demo.dto;

import java.math.BigDecimal;

public class BreachRuleDto {

    private BigDecimal penaltyPerDay;
    private BigDecimal maxPenaltyPercentage;
    private boolean active;

    public BreachRuleDto() {}

    public BreachRuleDto(BigDecimal penaltyPerDay, BigDecimal maxPenaltyPercentage, boolean active) {
        this.penaltyPerDay = penaltyPerDay;
        this.maxPenaltyPercentage = maxPenaltyPercentage;
        this.active = active;
    }

    public BigDecimal getPenaltyPerDay() {
        return penaltyPerDay;
    }

    public void setPenaltyPerDay(BigDecimal penaltyPerDay) {
        this.penaltyPerDay = penaltyPerDay;
    }

    public BigDecimal getMaxPenaltyPercentage() {
        return maxPenaltyPercentage;
    }

    public void setMaxPenaltyPercentage(BigDecimal maxPenaltyPercentage) {
        this.maxPenaltyPercentage = maxPenaltyPercentage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
