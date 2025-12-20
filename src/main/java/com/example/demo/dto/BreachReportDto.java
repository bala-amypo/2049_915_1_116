package com.example.demo.dto;

import java.math.BigDecimal;

public class BreachReportDto {

    private Long id;
    private Long contractId;
    private int daysDelayed;
    private BigDecimal penaltyAmount;

    public BreachReportDto() {}

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

    public int getDaysDelayed() {
        return daysDelayed;
    }
 
    public void setDaysDelayed(int daysDelayed) {
        this.daysDelayed = daysDelayed;
    }

    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }
 
    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }
}
