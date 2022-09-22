package com.restapi.financialfortressbackend.domain.valuation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class SimpleValuationDto {

    private Long id;
    protected LocalDateTime date;
    protected String type;
    protected BigDecimal valuation;
}
