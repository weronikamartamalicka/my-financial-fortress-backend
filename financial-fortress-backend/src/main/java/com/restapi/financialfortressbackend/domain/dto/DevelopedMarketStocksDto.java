package com.restapi.financialfortressbackend.domain.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DevelopedMarketStocksDto {

    private Long id;
    private static final String TYPE = "WIG5";
    private BigDecimal quantity;
    private BigDecimal commissionRate;
}
