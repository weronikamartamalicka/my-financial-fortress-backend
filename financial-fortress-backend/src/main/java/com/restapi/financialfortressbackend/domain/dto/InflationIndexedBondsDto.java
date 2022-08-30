package com.restapi.financialfortressbackend.domain.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class InflationIndexedBondsDto {

    private Long id;
    private static final String TYPE = "NMG7";
    private BigDecimal quantity;
    private BigDecimal interestRate;
    private LocalDate redemptionDate;
    private LocalDate interestPeriod;
    private BigDecimal commissionRate;
    private BigDecimal price;
}
