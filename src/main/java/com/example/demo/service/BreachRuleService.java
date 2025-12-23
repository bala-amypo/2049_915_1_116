package com.example.demo.service;

import com.example.demo.dto.BreachRuleDto;

import java.util.List;

public interface BreachRuleService {

    BreachRuleDto createRule(BreachRuleDto dto);

    List<BreachRuleDto> getActiveRules();

    void deactivateRule(Long id);
}
