package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    private final BreachRuleService service;

    public BreachRuleController(BreachRuleService service) {
        this.service = service;
    }

    @PostMapping
    public BreachRule create(@RequestBody BreachRule rule) {
        return service.createRule(rule);
    }

    @PutMapping("/{id}")
    public BreachRule update(@PathVariable Long id,
                             @RequestBody BreachRule rule) {
        return service.updateRule(id, rule);
    }

    @GetMapping
    public List<BreachRule> getAll() {
        return service.getAllRules();
    }

    @GetMapping("/{id}")
    public BreachRule getById(@PathVariable Long id) {
        return service.getRuleById(id);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateRule(id);
    }
}
