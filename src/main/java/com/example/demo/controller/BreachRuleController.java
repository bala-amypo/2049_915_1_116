package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.impl.BreachRuleServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    private final BreachRuleServiceImpl breachRuleService;

    public BreachRuleController(BreachRuleServiceImpl breachRuleService) {
        this.breachRuleService = breachRuleService;
    }

    @PostMapping
    public BreachRule create(@RequestBody BreachRule rule) {
        return breachRuleService.createRule(rule);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        breachRuleService.deactivateRule(id);
    }

    @GetMapping
    public List<BreachRule> getAll() {
        return breachRuleService.getAllRules();
    }
}
