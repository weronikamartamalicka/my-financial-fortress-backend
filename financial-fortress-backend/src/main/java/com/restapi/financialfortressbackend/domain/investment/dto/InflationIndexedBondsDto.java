package com.restapi.financialfortressbackend.domain.investment.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class InflationIndexedBondsDto extends SimpleInvestmentDto {

    private final BigDecimal firstYearInterestRate = BigDecimal.valueOf(0.07);
    private final BigDecimal interestRate = BigDecimal.valueOf(0.0175);
    private LocalDate redemptionDate;
    private final BigDecimal price = BigDecimal.valueOf(100);


    public InflationIndexedBondsDto(Long id, LocalDateTime date, BigDecimal quantity,
                                    LocalDate redemptionDate, BigDecimal entireValuation) {
        super(id, date,"ROD0934", quantity, entireValuation);
        this.redemptionDate = redemptionDate;
    }
}
