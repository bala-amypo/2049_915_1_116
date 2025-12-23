package com.example.demo.service.impl;

import com.example.demo.dto.BreachRuleDto;
import com.example.demo.entity.BreachRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository repository;

    public BreachRuleServiceImpl(BreachRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public BreachRuleDto createRule(BreachRuleDto dto) {

        BreachRule rule = BreachRule.builder()
                .ruleName(dto.getRuleName())
                .penaltyPerDay(dto.getPenaltyPerDay())
                .maxPenaltyPercentage(dto.getMaxPenaltyPercentage())
                .active(true)
                .defaultRule(dto.isDefaultRule())
                .build();

        repository.save(rule);
        return dto;
    }

    @Override
    public List<BreachRuleDto> getActiveRules() {

        return repository.findAll().stream()
                .filter(BreachRule::getActive)
                .map(r -> {
                    BreachRuleDto d = new BreachRuleDto();
                    d.setId(r.getId());
                    d.setRuleName(r.getRuleName());
                    d.setPenaltyPerDay(r.getPenaltyPerDay());
                    d.setMaxPenaltyPercentage(r.getMaxPenaltyPercentage());
                    d.setActive(r.getActive());
                    d.setDefaultRule(r.isDefaultRule());
                    return d;
                }).collect(Collectors.toList());
    }

    @Override
    public void deactivateRule(Long id) {

        BreachRule rule = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        rule.setActive(false);
        repository.save(rule);
    }
}
