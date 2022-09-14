package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GoldInvestmentDto {

    private Long id;
    private LocalDateTime date;
    public final String type = "Krugerrand 1/2 oz.";
    private BigDecimal quantity;
    public static final BigDecimal purchaseValuation = BigDecimal.valueOf(4667);
    private BigDecimal entireValuation;
}
