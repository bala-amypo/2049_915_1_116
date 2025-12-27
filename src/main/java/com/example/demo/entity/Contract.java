package com.example.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contract_number", nullable = false, unique = true)
    private String contractNumber;

    @Column(nullable = false)
    private String title;

    @Column(name = "base_contract_value", nullable = false)
    private BigDecimal baseContractValue;

    @Column(name = "agreed_delivery_date", nullable = false)
    private LocalDate agreedDeliveryDate;

    @Column(name = "counterparty_name", nullable = false)
    private String counterpartyName;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getBaseContractValue() {
        return baseContractValue;
    }

    public void setBaseContractValue(BigDecimal baseContractValue) {
        this.baseContractValue = baseContractValue;
    }

    public LocalDate getAgreedDeliveryDate() {
        return agreedDeliveryDate;
    }

    public void setAgreedDeliveryDate(LocalDate agreedDeliveryDate) {
        this.agreedDeliveryDate = agreedDeliveryDate;
    }

    public String getCounterpartyName() {
        return counterpartyName;
    }

    public void setCounterpartyName(String counterpartyName) {
        this.counterpartyName = counterpartyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
