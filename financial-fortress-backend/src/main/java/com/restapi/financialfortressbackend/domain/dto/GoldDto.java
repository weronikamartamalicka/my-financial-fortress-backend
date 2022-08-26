package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GoldDto {

    private Long id;
    private static final String TYPE = "Krugerrand";
    private LocalDateTime date;
    private BigDecimal entireValue;
}
