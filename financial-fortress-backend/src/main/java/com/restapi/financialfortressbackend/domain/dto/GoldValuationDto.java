package com.restapi.financialfortressbackend.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GoldValuationDto {

    private LocalDate date;
    private static final String TYPE = "Krugerrand";
    private BigDecimal saleValuation;
    private BigDecimal purchaseValuation;
    private BigDecimal entireValuation;
}
