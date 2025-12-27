package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository breachReportRepository;

    public BreachReportServiceImpl(BreachReportRepository breachReportRepository) {
        this.breachReportRepository = breachReportRepository;
    }

    @Override
    public BreachReport createReport(java.time.LocalDate startDate,
                                     java.time.LocalDate endDate) {

        long daysDelayed = ChronoUnit.DAYS.between(startDate, endDate);

        if (daysDelayed < 0) {
            daysDelayed = 0;
        }

        BreachReport breachReport = BreachReport.builder()
                .daysDelayed((int) daysDelayed)   // ✅ FIXED
                .remarks("Delivery delayed")
                .build();

        return breachReportRepository.save(breachReport);
    }
}
