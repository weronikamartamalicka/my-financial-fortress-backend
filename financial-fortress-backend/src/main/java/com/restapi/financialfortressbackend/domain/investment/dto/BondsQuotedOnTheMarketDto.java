package com.restapi.financialfortressbackend.domain.investment.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BondsQuotedOnTheMarketDto extends SimpleInvestmentDto {

    public final BigDecimal FACE_VALUE = BigDecimal.valueOf(1000);
    private BigDecimal couponRate;
    private LocalDate redemptionDate;
    private BigDecimal interestPeriod;

    public BondsQuotedOnTheMarketDto(Long id, LocalDateTime time, BigDecimal quantity,
                                     BigDecimal entireValuation,  BigDecimal couponRate,
                                     LocalDate redemptionDate, BigDecimal interestPeriod) {
        super(id, time, "10 Yr Gov Bond iShr Ix", quantity, entireValuation);
        this.couponRate = couponRate;
        this.redemptionDate = redemptionDate;
        this.interestPeriod = interestPeriod;
    }
}
