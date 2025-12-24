package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Contract {
    private Long id;
    private String contractNumber;
    private String title;
    private String counterpartyName;
    private LocalDate agreedDeliveryDate;
    private BigDecimal baseContractValue;
    private String status;
    private LocalDateTime createdAt;

    // No-argument constructor
    public Contract() {}

    // All-argument constructor
    public Contract(Long id, String contractNumber, String title, String counterpartyName, 
                   LocalDate agreedDeliveryDate, BigDecimal baseContractValue, String status, 
                   LocalDateTime createdAt) {
        this.id = id;
        this.contractNumber = contractNumber;
        this.title = title;
        this.counterpartyName = counterpartyName;
        this.agreedDeliveryDate = agreedDeliveryDate;
        this.baseContractValue = baseContractValue;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Builder pattern
    public static ContractBuilder builder() {
        return new ContractBuilder();
    }

    // Getters
    public Long getId() { return id; }
    public String getContractNumber() { return contractNumber; }
    public String getTitle() { return title; }
    public String getCounterpartyName() { return counterpartyName; }
    public LocalDate getAgreedDeliveryDate() { return agreedDeliveryDate; }
    public BigDecimal getBaseContractValue() { return baseContractValue; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setContractNumber(String contractNumber) { this.contractNumber = contractNumber; }
    public void setTitle(String title) { this.title = title; }
    public void setCounterpartyName(String counterpartyName) { this.counterpartyName = counterpartyName; }
    public void setAgreedDeliveryDate(LocalDate agreedDeliveryDate) { this.agreedDeliveryDate = agreedDeliveryDate; }
    public void setBaseContractValue(BigDecimal baseContractValue) { this.baseContractValue = baseContractValue; }
    public void setStatus(String status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Builder class
    public static class ContractBuilder {
        private Long id;
        private String contractNumber;
        private String title;
        private String counterpartyName;
        private LocalDate agreedDeliveryDate;
        private BigDecimal baseContractValue;
        private String status;
        private LocalDateTime createdAt;

        public ContractBuilder id(Long id) { this.id = id; return this; }
        public ContractBuilder contractNumber(String contractNumber) { this.contractNumber = contractNumber; return this; }
        public ContractBuilder title(String title) { this.title = title; return this; }
        public ContractBuilder counterpartyName(String counterpartyName) { this.counterpartyName = counterpartyName; return this; }
        public ContractBuilder agreedDeliveryDate(LocalDate agreedDeliveryDate) { this.agreedDeliveryDate = agreedDeliveryDate; return this; }
        public ContractBuilder baseContractValue(BigDecimal baseContractValue) { this.baseContractValue = baseContractValue; return this; }
        public ContractBuilder status(String status) { this.status = status; return this; }
        public ContractBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Contract build() {
            return new Contract(id, contractNumber, title, counterpartyName, agreedDeliveryDate, baseContractValue, status, createdAt);
        }
    }
}