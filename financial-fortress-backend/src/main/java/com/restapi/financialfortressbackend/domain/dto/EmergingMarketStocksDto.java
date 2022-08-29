package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class EmergingMarketStocksDto {

    private Long id;
    private static final String TYPE = "WIG5";
    private BigDecimal entireValuation;
    private LocalDate date;
}
