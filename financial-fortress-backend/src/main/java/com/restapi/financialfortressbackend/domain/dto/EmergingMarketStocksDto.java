package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class EmergingMarketStocksDto {

    private Long id;
    private LocalDateTime date;
    public final String type = "BofAML AAA-A Emerging Markets Corporate Ix";
    private BigDecimal quantity;
    private BigDecimal entireValuation;
}
