package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DevelopedMarketStocksDto {

    private Long id;
    private LocalDateTime date;
    public final String type = "MSCI China A DivAdj Ix";
    private BigDecimal quantity;
    private BigDecimal entireValuation;
}
