package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class InflationIndexedBondsDto {

    private Long id;
    private static final String TYPE = "NMG7";
    private LocalDate date;
    private BigDecimal commissionRate;
    private BigDecimal price;
    private BigDecimal entireValue;
}
