package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class GoldInvestmentDto {

    private Long id;
    public final String type = "Krugerrand 1/2 oz.";
    private BigDecimal quantity;
    public static final BigDecimal purchaseValuation = BigDecimal.valueOf(4667);
}
