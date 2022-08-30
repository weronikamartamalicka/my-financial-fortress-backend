package com.restapi.financialfortressbackend.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "INFLATION_INDEXED_BONDS")
public class InflationIndexedBondsInvestment {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "TYPE")
    public static final String TYPE = "NMG7";

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "CONST_INTEREST_RATE")
    private BigDecimal interestRate;

    @Column(name = "REDEMPTION")
    private LocalDate redemptionDate;

    @Column(name = "INTEREST_PERIOD")
    private LocalDate interestPeriod;



    public InflationIndexedBondsInvestment(BigDecimal quantity, BigDecimal interestRate,
                                           LocalDate redemptionDate, LocalDate interestPeriod
                                           ) {
        this.quantity = quantity;
        this.interestRate = interestRate;
        this.redemptionDate = redemptionDate;
        this.interestPeriod = interestPeriod;
    }
}
