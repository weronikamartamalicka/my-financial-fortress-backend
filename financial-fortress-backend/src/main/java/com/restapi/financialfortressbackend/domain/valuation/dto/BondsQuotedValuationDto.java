package com.restapi.financialfortressbackend.domain.valuation.dto;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
public class BondsQuotedValuationDto extends SimpleValuationDto {

    private BigDecimal commissionRate;

    public BondsQuotedValuationDto(Long id, LocalDateTime date, BigDecimal valuation, BigDecimal commissionRate) {
        super(id, date, InvestmentInstrumentName.BONDS_QUOTED.getName(), valuation);
        this.commissionRate = commissionRate;
    }
}
