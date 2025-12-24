package com.example.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class BreachRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String ruleName;
    private BigDecimal penaltyPerDay;
    private Double maxPenaltyPercentage;
    private Boolean active;
    private Boolean isDefaultRule;
    
    public BreachRule() {}
    
    public static BreachRuleBuilder builder() { return new BreachRuleBuilder(); }
    
    public static class BreachRuleBuilder {
        private BreachRule rule = new BreachRule();
        
        public BreachRuleBuilder id(Long id) { rule.id = id; return this; }
        public BreachRuleBuilder ruleName(String ruleName) { rule.ruleName = ruleName; return this; }
        public BreachRuleBuilder penaltyPerDay(BigDecimal penaltyPerDay) { rule.penaltyPerDay = penaltyPerDay; return this; }
        public BreachRuleBuilder maxPenaltyPercentage(Double maxPenaltyPercentage) { rule.maxPenaltyPercentage = maxPenaltyPercentage; return this; }
        public BreachRuleBuilder active(Boolean active) { rule.active = active; return this; }
        public BreachRuleBuilder isDefaultRule(Boolean isDefaultRule) { rule.isDefaultRule = isDefaultRule; return this; }
        
        public BreachRule build() { return rule; }
    }
    
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