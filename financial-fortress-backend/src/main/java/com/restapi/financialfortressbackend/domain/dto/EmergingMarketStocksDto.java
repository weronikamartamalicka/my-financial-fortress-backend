package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class EmergingMarketStocksDto {

    private Long id;
    public final String type = "BofAML AAA-A Emerging Markets Corporate Ix";
    private BigDecimal quantity;
}
