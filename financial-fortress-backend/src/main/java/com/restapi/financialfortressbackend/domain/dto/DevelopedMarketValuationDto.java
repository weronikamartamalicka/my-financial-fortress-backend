package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class DevelopedMarketValuationDto {

    private Long id;
    private LocalDate date;
    public final String type = "MSCI China A DivAdj Ix";
    private BigDecimal valuation;
    private BigDecimal commissionRate;
    private BigDecimal entireValuation;
}
