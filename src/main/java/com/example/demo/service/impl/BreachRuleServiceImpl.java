package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository breachRuleRepository;

    @Override
    public BreachRule createRule(BreachRule rule) {
        rule.setActive(true);
        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        BreachRule existing = getRuleById(id);
        rule.setId(existing.getId());
        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule getRuleById(Long id) {
        return breachRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Breach rule not found"));
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = getRuleById(id);
        rule.setActive(false);
        breachRuleRepository.save(rule);
    }
}
