package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String contractNumber;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String counterpartyName;

    @Column(nullable = false)
    private LocalDate agreedDeliveryDate;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal baseContractValue;

    @Column(nullable = false)
    private String status = "ACTIVE";

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Contract() {}

    public Contract(String contractNumber, String title, String counterpartyName, 
                   LocalDate agreedDeliveryDate, BigDecimal baseContractValue) {
        this.contractNumber = contractNumber;
        this.title = title;
        this.counterpartyName = counterpartyName;
        this.agreedDeliveryDate = agreedDeliveryDate;
        this.baseContractValue = baseContractValue;
    }

    
    public static ContractBuilder builder() {
        return new ContractBuilder();
    }

    public static class ContractBuilder {
        private Long id;
        private String contractNumber;
        private String title;
        private String counterpartyName;
        private LocalDate agreedDeliveryDate;
        private BigDecimal baseContractValue;
        private String status = "ACTIVE";

        public ContractBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ContractBuilder contractNumber(String contractNumber) {
            this.contractNumber = contractNumber;
            return this;
        }

        public ContractBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ContractBuilder counterpartyName(String counterpartyName) {
            this.counterpartyName = counterpartyName;
            return this;
        }

        public ContractBuilder agreedDeliveryDate(LocalDate agreedDeliveryDate) {
            this.agreedDeliveryDate = agreedDeliveryDate;
            return this;
        }

        public ContractBuilder baseContractValue(BigDecimal baseContractValue) {
            this.baseContractValue = baseContractValue;
            return this;
        }

        public ContractBuilder status(String status) {
            this.status = status;
            return this;
        }

        public Contract build() {
            Contract contract = new Contract();
            contract.id = this.id;
            contract.contractNumber = this.contractNumber;
            contract.title = this.title;
            contract.counterpartyName = this.counterpartyName;
            contract.agreedDeliveryDate = this.agreedDeliveryDate;
            contract.baseContractValue = this.baseContractValue;
            contract.status = this.status;
            return contract;
        }
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    
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

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}