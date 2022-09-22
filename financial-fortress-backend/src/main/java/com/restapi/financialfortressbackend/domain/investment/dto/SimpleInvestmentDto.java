package com.restapi.financialfortressbackend.domain.investment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class SimpleInvestmentDto {

    protected Long id;
    protected LocalDateTime date;
    protected String type;
    protected BigDecimal quantity;
    protected BigDecimal entireValuation;
}
