package com.example.demo.dto;

import java.time.LocalDate;

public class DeliveryRecordDto {

    private Long id;
    private Long contractId;
    private LocalDate deliveryDate;
    private int daysDelayed;

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

    public int getDaysDelayed() {
        return daysDelayed;
    }

    public void setDaysDelayed(int daysDelayed) {
        this.daysDelayed = daysDelayed;
    }
}
