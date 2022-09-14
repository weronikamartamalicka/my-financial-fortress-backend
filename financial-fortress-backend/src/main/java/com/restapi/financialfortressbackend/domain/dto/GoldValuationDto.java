package com.restapi.financialfortressbackend.domain.dto;

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
@AllArgsConstructor
public class GoldValuationDto {

    private Long id;
    private LocalDateTime date;
    public final String TYPE = "Krugerrand 1/2 oz.";
    private BigDecimal oneCoinPrice;
}
