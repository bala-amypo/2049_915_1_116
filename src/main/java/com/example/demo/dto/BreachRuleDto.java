package com.example.demo.dto;

import java.math.BigDecimal;

public class BreachRuleDto {

    private Long id;
    private String ruleName;
    private BigDecimal penaltyPerDay;
    private BigDecimal maxPenaltyPercentage;
    private boolean active;
    private boolean defaultRule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
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

    public boolean isDefaultRule() {
        return defaultRule;
    }

    public void setDefaultRule(boolean defaultRule) {
        this.defaultRule = defaultRule;
    }
}
