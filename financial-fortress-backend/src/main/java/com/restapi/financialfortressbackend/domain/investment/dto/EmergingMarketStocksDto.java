package com.restapi.financialfortressbackend.domain.investment.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class EmergingMarketStocksDto extends SimpleInvestmentDto {

    public EmergingMarketStocksDto(Long id, LocalDateTime date, BigDecimal quantity, BigDecimal entireValuation) {
        super(id, date, "BofAML AAA-A Emerging Markets Corporate Ix", quantity, entireValuation);
    }
}
