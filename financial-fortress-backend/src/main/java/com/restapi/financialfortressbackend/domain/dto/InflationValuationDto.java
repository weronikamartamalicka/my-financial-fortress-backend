package com.restapi.financialfortressbackend.domain.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
public class InflationValuationDto {

    private LocalDate date;
    private BigDecimal valuation;
    private BigDecimal interestsValuation;
    private BigDecimal entireValuation;
}
