package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachRuleServiceImpl {

    private final BreachRuleRepository breachRuleRepository;

    public BreachRuleServiceImpl(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }

    public BreachRule createRule(BreachRule rule) {
        rule.setActive(true);
        return breachRuleRepository.save(rule);
    }

    public void deactivateRule(Long id) {
        BreachRule rule = breachRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
        rule.setActive(false);
        breachRuleRepository.save(rule);
    }

    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
}
