package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class DevelopedMarketStocksDto {

    private Long id;
    public final String type = "MSCI China A DivAdj Ix";
    private BigDecimal quantity;
}
