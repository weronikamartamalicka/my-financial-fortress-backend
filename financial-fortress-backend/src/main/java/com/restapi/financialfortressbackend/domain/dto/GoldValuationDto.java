package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class GoldValuationDto {

    private Long id;
    private LocalDate date;
    public final String TYPE = "Krugerrand 1/2 oz.";
    private BigDecimal oneCoinPrice;
    private BigDecimal entireValuation;
}
