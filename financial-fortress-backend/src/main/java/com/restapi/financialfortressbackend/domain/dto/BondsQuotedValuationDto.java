package com.restapi.financialfortressbackend.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BondsQuotedValuationDto {

    private LocalDate date;
    private static final String TYPE = "NMG7";
    private BigDecimal valuation;
    private BigDecimal entireValuation;
}
