package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class InflationValuationDto {

    private Long id;
    public final String type = "ROD0934";
    private LocalDate date;
    private BigDecimal valuation;
    private BigDecimal interestsValuation;
    private BigDecimal entireValuation;
}
