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
public class InflationValuationDto {

    private Long id;
    public final String type = "ROD0934";
    private LocalDateTime date;
    private BigDecimal valuation;
    private BigDecimal interestsValuation;
}
