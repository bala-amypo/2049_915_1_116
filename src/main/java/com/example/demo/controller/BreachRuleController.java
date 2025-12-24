package com.example.demo.controller;

import com.example.demo.dto.BreachRuleDto;
import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breach-rules")
public class BreachRuleController {

    private final BreachRuleService service;

    public BreachRuleController(BreachRuleService service) {
        this.service = service;
    }

    @PostMapping
    public BreachRule create(@RequestBody BreachRuleDto dto) {

        BreachRule rule = BreachRule.builder()
                .ruleName(dto.getRuleName())
                .allowedDelayDays(dto.getAllowedDelayDays())
                .penaltyAmount(dto.getPenaltyAmount())
                .active(dto.isActive())
                .build();

        return service.createRule(rule);
    }

    @GetMapping
    public List<BreachRule> getAll() {
        return service.getAllRules();
    }
}
