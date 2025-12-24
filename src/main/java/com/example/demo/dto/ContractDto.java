package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContractDto {
    private String contractNumber;
    private String title;
    private String counterpartyName;
    private LocalDate agreedDeliveryDate;
    private BigDecimal baseContractValue;
    private String status;

    // No-argument constructor
    public ContractDto() {}

    // All-argument constructor
    public ContractDto(String contractNumber, String title, String counterpartyName, 
                      LocalDate agreedDeliveryDate, BigDecimal baseContractValue, String status) {
        this.contractNumber = contractNumber;
        this.title = title;
        this.counterpartyName = counterpartyName;
        this.agreedDeliveryDate = agreedDeliveryDate;
        this.baseContractValue = baseContractValue;
        this.status = status;
    }

    // Getters
    public String getContractNumber() { return contractNumber; }
    public String getTitle() { return title; }
    public String getCounterpartyName() { return counterpartyName; }
    public LocalDate getAgreedDeliveryDate() { return agreedDeliveryDate; }
    public BigDecimal getBaseContractValue() { return baseContractValue; }
    public String getStatus() { return status; }

    // Setters
    public void setContractNumber(String contractNumber) { this.contractNumber = contractNumber; }
    public void setTitle(String title) { this.title = title; }
    public void setCounterpartyName(String counterpartyName) { this.counterpartyName = counterpartyName; }
    public void setAgreedDeliveryDate(LocalDate agreedDeliveryDate) { this.agreedDeliveryDate = agreedDeliveryDate; }
    public void setBaseContractValue(BigDecimal baseContractValue) { this.baseContractValue = baseContractValue; }
    public void setStatus(String status) { this.status = status; }
}