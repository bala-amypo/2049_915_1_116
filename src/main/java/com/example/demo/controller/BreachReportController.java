package com.example.demo.controller;

import com.example.demo.dto.BreachReportDto;
import com.example.demo.entity.BreachReport;
import com.example.demo.entity.Contract;
import com.example.demo.service.BreachReportService;
import com.example.demo.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/breach-reports")
public class BreachReportController {

    private final BreachReportService service;
    private final ContractService contractService;

    public BreachReportController(BreachReportService service,
                                  ContractService contractService) {
        this.service = service;
        this.contractService = contractService;
    }

    @PostMapping
    public BreachReport generate(@RequestBody BreachReportDto dto) {

        Contract contract = contractService.getContractById(dto.getContractId());

        BreachReport report = BreachReport.builder()
                .contract(contract)
                .reportSummary(dto.getReportSummary())
                .generatedDate(LocalDate.now())
                .build();

        return service.generateReport(report);
    }
}
