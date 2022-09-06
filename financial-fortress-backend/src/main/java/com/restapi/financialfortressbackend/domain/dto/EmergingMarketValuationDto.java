package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class EmergingMarketValuationDto {

    private Long id;
    private LocalDate date;
    public final String type = "BofAML AAA-A Emerging Markets Corporate Ix";
    private BigDecimal valuation;
    private BigDecimal commissionRate;
    private BigDecimal entireValuation;
}
