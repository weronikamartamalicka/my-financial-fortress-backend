package com.restapi.financialfortressbackend.domain.investment.dto;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
public class GoldInvestmentDto extends SimpleInvestmentDto {

    public static final BigDecimal purchaseValuation = BigDecimal.valueOf(4667);

    public GoldInvestmentDto(Long id, LocalDateTime date, BigDecimal quantity, BigDecimal entireValuation) {
        super(id, date, InvestmentInstrumentName.GOLD.getName(), quantity, entireValuation);
    }
}
