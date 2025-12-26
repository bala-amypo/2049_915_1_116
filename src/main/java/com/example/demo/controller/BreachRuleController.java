package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BreachRule> createBreachRule(@RequestBody BreachRule rule) {
        BreachRule createdRule = breachRuleService.createRule(rule);
        return ResponseEntity.ok(createdRule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BreachRule> updateBreachRule(@PathVariable Long id, @RequestBody BreachRule rule) {
        BreachRule updatedRule = breachRuleService.updateRule(id, rule);
        return ResponseEntity.ok(updatedRule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreachRule> getBreachRule(@PathVariable Long id) {
        BreachRule rule = breachRuleService.getRuleById(id);
        return ResponseEntity.ok(rule);
    }

    @GetMapping
    public ResponseEntity<List<BreachRule>> getAllBreachRules() {
        List<BreachRule> rules = breachRuleService.getAllRules();
        return ResponseEntity.ok(rules);
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateBreachRule(@PathVariable Long id) {
        breachRuleService.deactivateRule(id);
        return ResponseEntity.ok().build();
    }
}