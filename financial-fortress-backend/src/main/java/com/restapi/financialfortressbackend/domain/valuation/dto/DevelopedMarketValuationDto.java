package com.restapi.financialfortressbackend.domain.valuation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
public class DevelopedMarketValuationDto extends SimpleValuationDto {

    private BigDecimal commissionRate;

    public DevelopedMarketValuationDto(Long id, LocalDateTime date, BigDecimal valuation, BigDecimal commissionRate) {
        super(id, date, "MSCI China A DivAdj Ix", valuation);
        this.commissionRate = commissionRate;
    }
}
