package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DeliveryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Contract contract;
    
    private LocalDate deliveryDate;
    private String notes;
    
    public DeliveryRecord() {}
    
    public static DeliveryRecordBuilder builder() {
        return new DeliveryRecordBuilder();
    }
    
    public static class DeliveryRecordBuilder {
        private DeliveryRecord record = new DeliveryRecord();
        
        public DeliveryRecordBuilder id(Long id) { record.id = id; return this; }
        public DeliveryRecordBuilder contract(Contract contract) { record.contract = contract; return this; }
        public DeliveryRecordBuilder deliveryDate(LocalDate deliveryDate) { record.deliveryDate = deliveryDate; return this; }
        public DeliveryRecordBuilder notes(String notes) { record.notes = notes; return this; }
        
        public DeliveryRecord build() { return record; }
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Contract getContract() { return contract; }
    public void setContract(Contract contract) { this.contract = contract; }
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}