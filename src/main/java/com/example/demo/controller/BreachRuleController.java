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
    public ResponseEntity<BreachRule> createRule(@RequestBody BreachRule rule) {
        return ResponseEntity.ok(breachRuleService.createRule(rule));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BreachRule> updateRule(@PathVariable Long id, @RequestBody BreachRule rule) {
        return ResponseEntity.ok(rule);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BreachRule> getRule(@PathVariable Long id) {
        return ResponseEntity.ok(breachRuleService.getActiveDefaultOrFirst());
    }
    
    @GetMapping
    public ResponseEntity<List<BreachRule>> getAllRules() {
        return ResponseEntity.ok(breachRuleService.getAllRules());
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateRule(@PathVariable Long id) {
        breachRuleService.deactivateRule(id);
        return ResponseEntity.ok().build();
    }
}