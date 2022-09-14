package com.restapi.financialfortressbackend.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "INFLATION_INDEXED_BONDS")
public class InflationIndexedBondsInvestment {

    @Id
    @NotNull
    @GeneratedValue()
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "TYPE")
    public final String type = "ROD0934";

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "FIRST_YEAR_INTEREST_RATE")
    private final BigDecimal firstYearInterestRate = BigDecimal.valueOf(0.07);

    @Column(name = "INTEREST_RATE")
    private final BigDecimal interestRate = BigDecimal.valueOf(0.0175);

    @Column(name = "REDEMPTION")
    private LocalDate redemptionDate;

    @Column(name = "ONE_BOND_PRICE")
    private final BigDecimal price = BigDecimal.valueOf(100);

    @Column(name = "ENTIRE_VALUATION")
    private BigDecimal entireValuation;

    public InflationIndexedBondsInvestment(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
