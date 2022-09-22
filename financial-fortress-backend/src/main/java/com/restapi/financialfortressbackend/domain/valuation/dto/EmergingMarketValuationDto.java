package com.restapi.financialfortressbackend.domain.valuation.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmergingMarketValuationDto extends SimpleValuationDto {


    private BigDecimal commissionRate;

    public EmergingMarketValuationDto(Long id, LocalDateTime date, BigDecimal valuation, BigDecimal commissionRate) {
        super(id, date,"BofAML AAA-A Emerging Markets Corporate Ix", valuation);
        this.commissionRate = commissionRate;
    }
}
