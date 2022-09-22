package com.restapi.financialfortressbackend.domain.investment;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "INFLATION_INDEXED_BONDS")
public class InflationIndexedBondsInvestment extends SimpleInvestment {

    @Column(name = "FIRST_YEAR_INTEREST_RATE")
    private final BigDecimal firstYearInterestRate = BigDecimal.valueOf(0.07);

    @Column(name = "INTEREST_RATE")
    private final BigDecimal interestRate = BigDecimal.valueOf(0.0175);

    @Column(name = "REDEMPTION")
    private LocalDate redemptionDate;

    @Column(name = "ONE_BOND_PRICE")
    private final BigDecimal price = BigDecimal.valueOf(100);

    public InflationIndexedBondsInvestment() {
        super(InvestmentInstrumentName.BONDS_INDEXED.getName());
    }

    public InflationIndexedBondsInvestment(BigDecimal quantity) {
        super(InvestmentInstrumentName.BONDS_INDEXED.getName(), quantity);
    }
}
