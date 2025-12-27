package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private BreachRuleRepository breachRuleRepository;

    public BreachRuleServiceImpl() {
        this.breachRuleRepository = null;
    }

    public BreachRuleServiceImpl(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        validateBreachRule(rule);
        
        if (breachRuleRepository.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new BadRequestException("Rule with planName exists");
        }

        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        BreachRule existingRule = breachRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Breach rule not found"));
        
        validateBreachRule(rule);
        
        Optional<BreachRule> duplicateRule = breachRuleRepository.findByRuleName(rule.getRuleName());
        if (duplicateRule.isPresent() && !duplicateRule.get().getId().equals(id)) {
            throw new BadRequestException("Rule with planName exists");
        }

        existingRule.setRuleName(rule.getRuleName());
        existingRule.setPenaltyPerDay(rule.getPenaltyPerDay());
        existingRule.setMaxPenaltyPercentage(rule.getMaxPenaltyPercentage());
        if (rule.getActive() != null) {
            existingRule.setActive(rule.getActive());
        }
        if (rule.getIsDefaultRule() != null) {
            existingRule.setIsDefaultRule(rule.getIsDefaultRule());
        }

        return breachRuleRepository.save(existingRule);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        Optional<BreachRule> defaultRule = breachRuleRepository.findFirstByActiveTrueOrderByIsDefaultRuleDesc();
        if (defaultRule.isPresent()) {
            return defaultRule.get();
        }
        
        throw new ResourceNotFoundException("No active breach rule found");
    }

    @Override
    public BreachRule getBreachRuleById(Long id) {
        return breachRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Breach rule not found"));
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = breachRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
        
        rule.setActive(false);
        breachRuleRepository.save(rule);
    }

    private void validateBreachRule(BreachRule rule) {
        if (rule.getPenaltyPerDay() == null || rule.getPenaltyPerDay().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Penalty per day must be greater than zero");
        }
        
        if (rule.getMaxPenaltyPercentage() == null || rule.getMaxPenaltyPercentage() < 0 || rule.getMaxPenaltyPercentage() > 100) {
            throw new IllegalArgumentException("Max penalty percentage must be between 0 and 100");
        }
    }
}