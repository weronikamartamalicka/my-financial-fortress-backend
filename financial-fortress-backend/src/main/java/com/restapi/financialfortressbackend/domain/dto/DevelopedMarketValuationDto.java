package com.restapi.financialfortressbackend.domain.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
public class DevelopedMarketValuationDto {

    private LocalDate date;
    private static final String TYPE = "WIG5";
    private BigDecimal valuation;
    private BigDecimal entireValuation;
}