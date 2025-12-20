package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "breach_reports")
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contractId;

    private Integer totalDelayDays;

    private Double totalPenaltyAmount;

    private LocalDate reportDate;

    public BreachReport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Integer getTotalDelayDays() {
        return totalDelayDays;
    }

    public void setTotalDelayDays(Integer totalDelayDays) {
        this.totalDelayDays = totalDelayDays;
    }

    public Double getTotalPenaltyAmount() {
        return totalPenaltyAmount;
    }

    public void setTotalPenaltyAmount(Double totalPenaltyAmount) {
        this.totalPenaltyAmount = totalPenaltyAmount;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }
}
