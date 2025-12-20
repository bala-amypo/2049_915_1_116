package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto {

    private Long id;
    private String contractNumber;
    private String clientName;
    private Double contractValue;
    private String startDate;
    private String endDate;

}
