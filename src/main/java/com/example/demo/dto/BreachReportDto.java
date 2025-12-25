package com.example.demo.dto;

import java.math.BigDecimal;

public class BreachReportDto {

    private Long contractId;
    private int daysDelayed;
    private BigDecimal calculatedPenalty;

    public BreachReportDto() {}

    public BreachReportDto(Long contractId, int daysDelayed, BigDecimal calculatedPenalty) {
        this.contractId = contractId;
        this.daysDelayed = daysDelayed;
        this.calculatedPenalty = calculatedPenalty;
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

    public BigDecimal getCalculatedPenalty() {
        return calculatedPenalty;
    }

    public void setCalculatedPenalty(BigDecimal calculatedPenalty) {
        this.calculatedPenalty = calculatedPenalty;
    }
}
