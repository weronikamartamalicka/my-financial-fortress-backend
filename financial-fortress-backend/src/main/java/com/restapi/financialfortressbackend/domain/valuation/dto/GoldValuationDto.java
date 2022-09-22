package com.restapi.financialfortressbackend.domain.valuation.dto;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class GoldValuationDto extends SimpleValuationDto {

    public GoldValuationDto(Long id, LocalDateTime date, BigDecimal valuation) {
        super(id, date, InvestmentInstrumentName.GOLD.getName(), valuation);
    }
}
