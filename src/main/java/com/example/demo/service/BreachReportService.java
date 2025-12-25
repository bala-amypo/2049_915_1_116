package com.example.demo.service;

import com.example.demo.entity.BreachReport;

public interface BreachReportService {

    BreachReport generateReport(Long contractId);
}
