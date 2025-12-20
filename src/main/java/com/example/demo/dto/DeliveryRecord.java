package com.example.demo.dto;

import java.time.LocalDate;

public class DeliveryRecordDto {

    private Long id;
    private Long contractId;
    private LocalDate deliveryDate;
    private String remarks;

    public DeliveryRecordDto() {}

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

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }
 
    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getRemarks() {
        return remarks;
    }
 
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
