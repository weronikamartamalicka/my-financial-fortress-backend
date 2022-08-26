package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DevelopedMarketStocksDto {

    private Long id;
    private static final String TYPE = "WIG5";
    private BigDecimal entireValuation;
    private LocalDateTime date;
}
