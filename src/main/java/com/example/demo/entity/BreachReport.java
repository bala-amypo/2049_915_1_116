package com.example.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class BreachReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Contract contract;
    
    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    
    public BreachReport() {}
    
    public static BreachReportBuilder builder() { return new BreachReportBuilder(); }
    
    public static class BreachReportBuilder {
        private BreachReport report = new BreachReport();
        
        public BreachReportBuilder id(Long id) { report.id = id; return this; }
        public BreachReportBuilder contract(Contract contract) { report.contract = contract; return this; }
        public BreachReportBuilder daysDelayed(Integer daysDelayed) { report.daysDelayed = daysDelayed; return this; }
        public BreachReportBuilder penaltyAmount(BigDecimal penaltyAmount) { report.penaltyAmount = penaltyAmount; return this; }
        
        public BreachReport build() { return report; }
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Contract getContract() { return contract; }
    public void setContract(Contract contract) { this.contract = contract; }
    public Integer getDaysDelayed() { return daysDelayed; }
    public void setDaysDelayed(Integer daysDelayed) { this.daysDelayed = daysDelayed; }
    public BigDecimal getPenaltyAmount() { return penaltyAmount; }
    public void setPenaltyAmount(BigDecimal penaltyAmount) { this.penaltyAmount = penaltyAmount; }
}