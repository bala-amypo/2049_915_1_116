
package com.example.demo.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyCalculationDto {  
    private Long id;
    private Long contractId;
    private int daysDelayed;
    private BigDecimal calculatedPenalty;
}
