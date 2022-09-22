package com.restapi.financialfortressbackend.domain.valuation.dto;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
public class EmergingMarketValuationDto extends SimpleValuationDto {

    private BigDecimal commissionRate;

    public EmergingMarketValuationDto(Long id, LocalDateTime date, BigDecimal valuation, BigDecimal commissionRate) {
        super(id, date, InvestmentInstrumentName.EMERGING_ETF.getName(), valuation);
        this.commissionRate = commissionRate;
    }
}
