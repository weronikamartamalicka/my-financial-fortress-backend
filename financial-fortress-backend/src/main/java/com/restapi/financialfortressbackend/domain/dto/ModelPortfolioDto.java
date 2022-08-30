package com.restapi.financialfortressbackend.domain.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class ModelPortfolioDto {

    private LocalDate date;
    private BigDecimal entireValue;
    private BigDecimal goldValue;
    private BigDecimal bondsQuotedValue;
    private BigDecimal bondsIndexedValue;
    private BigDecimal developedMarketValue;
    private BigDecimal emergingMarketValue;
    private BigDecimal goldPercentage;
    private BigDecimal bondsQuotedPercentage;
    private BigDecimal bondsIndexedPercentage;
    private BigDecimal developedMarketPercentage;
    private BigDecimal emergingMarketPercentage;
}
