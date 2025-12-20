package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    private final BreachRuleService breachRuleService;

    public BreachRuleController(BreachRuleService breachRuleService) {
        this.breachRuleService = breachRuleService;
    }

    @PostMapping
    public BreachRule createBreachRule(@RequestBody BreachRule rule) {
        return breachRuleService.saveBreachRule(rule);
    }

    @GetMapping
    public List<BreachRule> getAllBreachRules() {
        return breachRuleService.getAllBreachRules();
    }

    @GetMapping("/{id}")
    public BreachRule getBreachRuleById(@PathVariable Long id) {
        return breachRuleService.getBreachRuleById(id);
    }
}
