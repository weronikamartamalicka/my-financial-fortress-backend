package com.restapi.financialfortressbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class BondsQuotedOnTheMarketDto {

    private Long id;
    public final String TYPE = "NMG7";
    private BigDecimal quantity;
    public final BigDecimal FACE_VALUE = BigDecimal.valueOf(1000);
    private BigDecimal commissionRate;
    private BigDecimal couponRate;
    private LocalDate redemptionDate;
    private BigDecimal interestPeriod;

}
