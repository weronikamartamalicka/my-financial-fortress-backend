package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class InflationIndexedBondsDto {

    private Long id;
    private LocalDateTime date;
    public final String type = "ROD0934";
    private BigDecimal quantity;
    private final BigDecimal firstYearInterestRate = BigDecimal.valueOf(0.07);
    private final BigDecimal interestRate = BigDecimal.valueOf(0.0175);
    private LocalDate redemptionDate;
    private final BigDecimal price = BigDecimal.valueOf(100);
    private BigDecimal entireValuation;
}
