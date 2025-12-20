package com.example.demo.service;

import com.example.demo.entity.BreachRule;
import java.util.List;

public interface BreachRuleService {

    BreachRule saveBreachRule(BreachRule rule);

    List<BreachRule> getAllBreachRules();

    BreachRule getBreachRuleById(Long id);
}
