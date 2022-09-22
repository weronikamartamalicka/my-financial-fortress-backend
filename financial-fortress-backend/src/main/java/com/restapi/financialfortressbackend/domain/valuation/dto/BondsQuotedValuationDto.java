package com.restapi.financialfortressbackend.domain.valuation.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class BondsQuotedValuationDto extends SimpleValuationDto {

    private BigDecimal commissionRate;

    public BondsQuotedValuationDto(Long id, LocalDateTime date, BigDecimal valuation, BigDecimal commissionRate) {
        super(id, date,"10 Yr Gov Bond iShr Ix" , valuation);
        this.commissionRate = commissionRate;
    }
}
