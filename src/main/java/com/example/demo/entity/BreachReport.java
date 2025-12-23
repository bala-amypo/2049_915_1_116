package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    private BigDecimal totalPenalty;
    private LocalDateTime generatedAt;

    public static BreachReportBuilder builder() {
        return new BreachReportBuilder();
    }

    public static class BreachReportBuilder {
        private BreachReport br = new BreachReport();
        public BreachReportBuilder contract(Contract c){ br.contract=c; return this;}
        public BreachReportBuilder totalPenalty(BigDecimal p){ br.totalPenalty=p; return this;}
        public BreachReportBuilder generatedAt(LocalDateTime t){ br.generatedAt=t; return this;}
        public BreachReport build(){ return br;}
    }
}
