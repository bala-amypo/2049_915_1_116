package com.example.demo.controller;

import com.example.demo.entity.Contract;
import com.example.demo.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Contract> create(@RequestBody Contract c) {
        return ResponseEntity.ok(service.createContract(c));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contract> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getContractById(id));
    }

    @GetMapping
    public ResponseEntity<List<Contract>> all() {
        return ResponseEntity.ok(service.getAllContracts());
    }
}
