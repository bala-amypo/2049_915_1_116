package com.example.demo.service;

import com.example.demo.entity.BreachReport;
import java.util.List;

public interface BreachReportService {

    BreachReport saveBreachReport(BreachReport report);

    List<BreachReport> getAllBreachReports();

    BreachReport getBreachReportById(Long id);
}
