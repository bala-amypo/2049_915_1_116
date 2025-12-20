package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DeliveryRecordDto {

    private Long id;
    private Long contractId;
    private LocalDate deliveryDate;
    private String notes;
}
