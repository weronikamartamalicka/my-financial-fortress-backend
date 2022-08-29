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
    private static final String TYPE = "NMG7";

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "INFLATION_RATE")
    private BigDecimal inflationRate;

    @Column(name = "INTEREST_RATE")
    private BigDecimal interestRate;

    @Column(name = "REDEMPTION")
    private LocalDate redemptionDate;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "INTEREST_PERIOD")
    private LocalDate interestPeriod;

    @Column(name = "COMMISSION")
    private BigDecimal commissionRate;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "ENTIRE_VALUE")
    private BigDecimal entireValue;

    public InflationIndexedBondsInvestment(BigDecimal quantity, BigDecimal inflationRate,
                                           BigDecimal interestRate, LocalDate redemptionDate,
                                           LocalDate date, LocalDate interestPeriod, BigDecimal commissionRate,
                                           BigDecimal price, BigDecimal entireValue) {
        this.quantity = quantity;
        this.inflationRate = inflationRate;
        this.interestRate = interestRate;
        this.redemptionDate = redemptionDate;
        this.date = date;
        this.interestPeriod = interestPeriod;
        this.commissionRate = commissionRate;
        this.price = price;
        this.entireValue = entireValue;
    }
}
