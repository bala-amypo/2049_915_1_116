package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.Contract;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final ContractRepository contractRepository;
    private final BreachReportRepository repository;

    public BreachReportServiceImpl(ContractRepository contractRepository,
                                   BreachReportRepository repository) {
        this.contractRepository = contractRepository;
        this.repository = repository;
    }

    @Override
    public BreachReport generateReport(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        BreachReport report = BreachReport.builder()
                .contract(contract)
                .totalPenalty(contract.getBaseContractValue())
                .generatedAt(LocalDateTime.now())
                .build();

        return repository.save(report);
    }
}
