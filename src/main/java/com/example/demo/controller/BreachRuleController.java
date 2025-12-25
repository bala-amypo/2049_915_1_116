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
    public BreachRule createRule(@RequestBody BreachRule rule) {
        return breachRuleService.createRule(rule);
    }

    @GetMapping("/{name}")
    public BreachRule getRuleByName(@PathVariable String name) {
        return breachRuleService.getRuleByName(name);
    }

    @GetMapping
    public List<BreachRule> getAllRules() {
        return breachRuleService.getAllRules();
    }
}
