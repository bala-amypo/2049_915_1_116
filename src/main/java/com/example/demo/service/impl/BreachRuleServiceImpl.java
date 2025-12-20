package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
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
    public BreachRule saveBreachRule(BreachRule rule) {
        return breachRuleRepository.save(rule);
    }

    @Override
    public List<BreachRule> getAllBreachRules() {
        return breachRuleRepository.findAll();
    }

    @Override
    public BreachRule getBreachRuleById(Long id) {
        return breachRuleRepository.findById(id).orElse(null);
    }
}
