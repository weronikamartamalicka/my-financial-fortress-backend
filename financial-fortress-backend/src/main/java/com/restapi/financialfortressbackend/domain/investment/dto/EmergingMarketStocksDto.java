package com.restapi.financialfortressbackend.domain.investment.dto;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EmergingMarketStocksDto extends SimpleInvestmentDto {

    public EmergingMarketStocksDto(Long id, LocalDateTime date, BigDecimal quantity, BigDecimal entireValuation) {
        super(id, date, InvestmentInstrumentName.EMERGING_ETF.getName(), quantity, entireValuation);
    }
}
