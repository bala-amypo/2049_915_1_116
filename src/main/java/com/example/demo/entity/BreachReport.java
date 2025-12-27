package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "breach_reports")
public class BreachReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @Column(nullable = false)
    private Integer daysDelayed;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal penaltyAmount;

    @Column(nullable = false)
    private String reportStatus = "GENERATED";

    @Column(nullable = false, updatable = false)
    private LocalDateTime generatedAt;

    public BreachReport() {}

    public BreachReport(Contract contract, Integer daysDelayed, BigDecimal penaltyAmount) {
        this.contract = contract;
        this.daysDelayed = daysDelayed;
        this.penaltyAmount = penaltyAmount;
    }

    // Builder pattern
    public static BreachReportBuilder builder() {
        return new BreachReportBuilder();
    }

    public static class BreachReportBuilder {
        private Long id;
        private Contract contract;
        private Integer daysDelayed;
        private BigDecimal penaltyAmount;
        private String reportStatus = "GENERATED";

        public BreachReportBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BreachReportBuilder contract(Contract contract) {
            this.contract = contract;
            return this;
        }

        public BreachReportBuilder daysDelayed(Integer daysDelayed) {
            this.daysDelayed = daysDelayed;
            return this;
        }

        public BreachReportBuilder penaltyAmount(BigDecimal penaltyAmount) {
            this.penaltyAmount = penaltyAmount;
            return this;
        }

        public BreachReportBuilder reportStatus(String reportStatus) {
            this.reportStatus = reportStatus;
            return this;
        }

        public BreachReport build() {
            BreachReport report = new BreachReport();
            report.id = this.id;
            report.contract = this.contract;
            report.daysDelayed = this.daysDelayed;
            report.penaltyAmount = this.penaltyAmount;
            report.reportStatus = this.reportStatus;
            return report;
        }
    }

    @PrePersist
    protected void onCreate() {
        generatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Contract getContract() { return contract; }
    public void setContract(Contract contract) { this.contract = contract; }

    public Integer getDaysDelayed() { return daysDelayed; }
    public void setDaysDelayed(Integer daysDelayed) { this.daysDelayed = daysDelayed; }

    public BigDecimal getPenaltyAmount() { return penaltyAmount; }
    public void setPenaltyAmount(BigDecimal penaltyAmount) { this.penaltyAmount = penaltyAmount; }

    public String getReportStatus() { return reportStatus; }
    public void setReportStatus(String reportStatus) { this.reportStatus = reportStatus; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}