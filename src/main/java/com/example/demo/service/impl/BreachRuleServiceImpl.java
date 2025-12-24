package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;

import java.util.List;

public class BreachRuleServiceImpl implements BreachRuleService {

    private BreachRuleRepository breachRuleRepository;

    @Override
    public BreachRule createRule(BreachRule rule) {
        if (rule.getPenaltyPerDay() == null
                || rule.getPenaltyPerDay().doubleValue() <= 0) {
            throw new IllegalArgumentException("Penalty per day must be > 0");
        }
        if (rule.getMaxPenaltyPercentage() > 100) {
            throw new IllegalArgumentException("Invalid max percentage");
        }
        return breachRuleRepository.save(rule);
    }

    @Override
    public void deactivateRule(Long ruleId) {
        BreachRule rule = breachRuleRepository.findById(ruleId)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
        rule.setActive(false);
        breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new RuntimeException("No active breach rule"));
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
}
