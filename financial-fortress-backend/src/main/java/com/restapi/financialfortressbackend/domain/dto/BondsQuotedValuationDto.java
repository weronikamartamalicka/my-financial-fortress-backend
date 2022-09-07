package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BondsQuotedValuationDto {

    private Long id;
    private LocalDate date;
    public final String type = "10 Yr Gov Bond iShr Ix";
    private BigDecimal valuation;
    private BigDecimal commissionRate;
    private BigDecimal entireValuation;
}
