package com.restapi.financialfortressbackend.domain.investment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ModelPortfolioDto {

    private Long id;
    private LocalDateTime date;
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
