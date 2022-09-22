package com.restapi.financialfortressbackend.domain.valuation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;


public class GoldValuationDto extends SimpleValuationDto {


    public GoldValuationDto(Long id, LocalDateTime date, BigDecimal valuation) {
        super(id, date, "Krugerrand 1/2 oz.", valuation);
    }
}
