package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository breachRuleRepository;

    public BreachRuleServiceImpl(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {

        if (rule.getPenaltyPerDay() == null ||
                rule.getPenaltyPerDay().doubleValue() <= 0) {
            throw new IllegalArgumentException("Penalty per day must be greater than zero");
        }

        if (rule.getMaxPenaltyPercentage() > 100) {
            throw new IllegalArgumentException("Max penalty percentage must be between 0 and 100");
        }

        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule getRuleByName(String name) {
        return breachRuleRepository.findByRuleName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
}
