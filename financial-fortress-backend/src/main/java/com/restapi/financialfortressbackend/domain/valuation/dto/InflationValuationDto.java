package com.restapi.financialfortressbackend.domain.valuation.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class InflationValuationDto extends SimpleValuationDto {

    private BigDecimal interestsValuation;

    public InflationValuationDto(Long id, LocalDateTime date, BigDecimal valuation, BigDecimal interestsValuation) {
        super(id, date, "ROD0934", valuation);
        this.interestsValuation = interestsValuation;
    }
}
