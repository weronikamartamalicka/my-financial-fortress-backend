package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class BondsQuotedOnTheMarketDto {

    private Long id;
    private static final String TYPE = "NMG7";
    private BigDecimal entireValuation;
    private BigDecimal commissionRate;
    private LocalDate redemptionDate;
    private LocalDate date;
}
