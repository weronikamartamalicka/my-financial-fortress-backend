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
public class BondsQuotedOnTheMarketDto {

    private Long id;
    private LocalDateTime date;
    public final String type = "10 Yr Gov Bond iShr Ix";
    private BigDecimal quantity;
    public final BigDecimal FACE_VALUE = BigDecimal.valueOf(1000);
    private BigDecimal couponRate;
    private LocalDate redemptionDate;
    private BigDecimal interestPeriod;
    private BigDecimal entireValuation;
}
