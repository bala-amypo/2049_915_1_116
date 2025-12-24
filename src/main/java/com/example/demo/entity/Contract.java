package com.example.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String contractNumber;
    private String title;
    private String counterpartyName;
    private LocalDate agreedDeliveryDate;
    private BigDecimal baseContractValue;
    private String status;
    private LocalDateTime createdAt;
    
    public Contract() {}
    
    public static ContractBuilder builder() {
        return new ContractBuilder();
    }
    
    public static class ContractBuilder {
        private Contract contract = new Contract();
        
        public ContractBuilder id(Long id) { contract.id = id; return this; }
        public ContractBuilder contractNumber(String contractNumber) { contract.contractNumber = contractNumber; return this; }
        public ContractBuilder title(String title) { contract.title = title; return this; }
        public ContractBuilder counterpartyName(String counterpartyName) { contract.counterpartyName = counterpartyName; return this; }
        public ContractBuilder agreedDeliveryDate(LocalDate agreedDeliveryDate) { contract.agreedDeliveryDate = agreedDeliveryDate; return this; }
        public ContractBuilder baseContractValue(BigDecimal baseContractValue) { contract.baseContractValue = baseContractValue; return this; }
        public ContractBuilder status(String status) { contract.status = status; return this; }
        public ContractBuilder createdAt(LocalDateTime createdAt) { contract.createdAt = createdAt; return this; }
        
        public Contract build() { return contract; }
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getContractNumber() { return contractNumber; }
    public void setContractNumber(String contractNumber) { this.contractNumber = contractNumber; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCounterpartyName() { return counterpartyName; }
    public void setCounterpartyName(String counterpartyName) { this.counterpartyName = counterpartyName; }
    public LocalDate getAgreedDeliveryDate() { return agreedDeliveryDate; }
    public void setAgreedDeliveryDate(LocalDate agreedDeliveryDate) { this.agreedDeliveryDate = agreedDeliveryDate; }
    public BigDecimal getBaseContractValue() { return baseContractValue; }
    public void setBaseContractValue(BigDecimal baseContractValue) { this.baseContractValue = baseContractValue; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}