package com.restapi.financialfortressbackend.domain.investment.dto;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class DevelopedMarketStocksDto extends SimpleInvestmentDto {

    public DevelopedMarketStocksDto(Long id, LocalDateTime date, BigDecimal quantity, BigDecimal entireValuation) {
        super(id, date, InvestmentInstrumentName.DEVELOPED_ETF.getName(), quantity, entireValuation);
    }
}
