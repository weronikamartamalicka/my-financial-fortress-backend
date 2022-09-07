package com.restapi.financialfortressbackend.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Entity(name = "INFLATION_INDEXED_BONDS")
public class InflationIndexedBondsInvestment {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

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

    public InflationIndexedBondsInvestment(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
