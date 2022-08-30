package com.restapi.financialfortressbackend.domain.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class GoldDto {

    private Long id;
    private static final String TYPE = "Krugerrand";
    private BigDecimal quantity;
}
