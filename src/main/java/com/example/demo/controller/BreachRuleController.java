package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    @Autowired
    private BreachRuleService breachRuleService;

    @PostMapping
    public ResponseEntity<BreachRule> createRule(@RequestBody BreachRule rule) {
        return ResponseEntity.ok(breachRuleService.createRule(rule));
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

    @GetMapping("/active-default")
    public ResponseEntity<BreachRule> getActiveDefaultRule() {
        return ResponseEntity.ok(breachRuleService.getActiveDefaultOrFirst());
    }
}