package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "breach_rules")
public class BreachRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ruleName;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal penaltyPerDay;

    @Column(nullable = false)
    private Double maxPenaltyPercentage;

    @Column(nullable = false)
    private Boolean active = true;

    private Boolean isDefaultRule;

    public BreachRule() {}

    public BreachRule(String ruleName, BigDecimal penaltyPerDay, Double maxPenaltyPercentage) {
        this.ruleName = ruleName;
        this.penaltyPerDay = penaltyPerDay;
        this.maxPenaltyPercentage = maxPenaltyPercentage;
    }

    
    public static BreachRuleBuilder builder() {
        return new BreachRuleBuilder();
    }

    public static class BreachRuleBuilder {
        private Long id;
        private String ruleName;
        private BigDecimal penaltyPerDay;
        private Double maxPenaltyPercentage;
        private Boolean active = true;
        private Boolean isDefaultRule;

        public BreachRuleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BreachRuleBuilder ruleName(String ruleName) {
            this.ruleName = ruleName;
            return this;
        }

        public BreachRuleBuilder penaltyPerDay(BigDecimal penaltyPerDay) {
            this.penaltyPerDay = penaltyPerDay;
            return this;
        }

        public BreachRuleBuilder maxPenaltyPercentage(Double maxPenaltyPercentage) {
            this.maxPenaltyPercentage = maxPenaltyPercentage;
            return this;
        }

        public BreachRuleBuilder active(Boolean active) {
            this.active = active;
            return this;
        }

        public BreachRuleBuilder isDefaultRule(Boolean isDefaultRule) {
            this.isDefaultRule = isDefaultRule;
            return this;
        }

        public BreachRule build() {
            BreachRule rule = new BreachRule();
            rule.id = this.id;
            rule.ruleName = this.ruleName;
            rule.penaltyPerDay = this.penaltyPerDay;
            rule.maxPenaltyPercentage = this.maxPenaltyPercentage;
            rule.active = this.active;
            rule.isDefaultRule = this.isDefaultRule;
            return rule;
        }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public BigDecimal getPenaltyPerDay() { return penaltyPerDay; }
    public void setPenaltyPerDay(BigDecimal penaltyPerDay) { this.penaltyPerDay = penaltyPerDay; }

    public Double getMaxPenaltyPercentage() { return maxPenaltyPercentage; }
    public void setMaxPenaltyPercentage(Double maxPenaltyPercentage) { this.maxPenaltyPercentage = maxPenaltyPercentage; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Boolean getIsDefaultRule() { return isDefaultRule; }
    public void setIsDefaultRule(Boolean isDefaultRule) { this.isDefaultRule = isDefaultRule; }
}