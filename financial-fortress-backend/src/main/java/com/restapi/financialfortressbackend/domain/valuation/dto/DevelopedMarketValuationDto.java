package com.restapi.financialfortressbackend.domain.valuation.dto;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
public class DevelopedMarketValuationDto extends SimpleValuationDto {

    private BigDecimal commissionRate;

    public DevelopedMarketValuationDto(Long id, LocalDateTime date, BigDecimal valuation, BigDecimal commissionRate) {
        super(id, date, InvestmentInstrumentName.DEVELOPED_ETF.getName(), valuation);
        this.commissionRate = commissionRate;
    }
}
