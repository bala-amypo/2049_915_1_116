package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleStatusController {

    @GetMapping("/simple-status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("OK");
    }
}