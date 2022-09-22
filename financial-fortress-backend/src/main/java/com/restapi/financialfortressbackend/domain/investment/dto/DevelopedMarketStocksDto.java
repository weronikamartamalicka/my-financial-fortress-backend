package com.restapi.financialfortressbackend.domain.investment.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class DevelopedMarketStocksDto extends SimpleInvestmentDto {

    public DevelopedMarketStocksDto(Long id, LocalDateTime date, BigDecimal quantity, BigDecimal entireValuation) {
        super(id, date, "MSCI China A DivAdj Ix", quantity, entireValuation);
    }
}
