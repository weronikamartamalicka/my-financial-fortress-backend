package com.restapi.financialfortressbackend.domain.investment.dto;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class BondsQuotedOnTheMarketDto extends SimpleInvestmentDto {

    private BigDecimal FACE_VALUE = BigDecimal.valueOf(1000);
    private BigDecimal couponRate;
    private LocalDate redemptionDate;
    private BigDecimal interestPeriod;

    public BondsQuotedOnTheMarketDto(Long id, LocalDateTime time, BigDecimal quantity,
                                     BigDecimal entireValuation,BigDecimal faceValue, BigDecimal couponRate,
                                     LocalDate redemptionDate, BigDecimal interestPeriod) {
        super(id, time, InvestmentInstrumentName.BONDS_QUOTED.getName(), quantity, entireValuation);
        this.FACE_VALUE = faceValue;
        this.couponRate = couponRate;
        this.redemptionDate = redemptionDate;
        this.interestPeriod = interestPeriod;
    }
}
