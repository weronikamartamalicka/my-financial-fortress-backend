package com.restapi.financialfortressbackend.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "GOLD_VALUATION")
public class GoldValuation {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "DATE", unique = true)
    private LocalDate date;

    @Column(name = "TYPE")
    public final String TYPE = "Krugerrand 1/2 oz.";

    @Column(name = "MARKET_PRICE")
    private BigDecimal marketPrice;

    @Column(name = "COIN_PRICE")
    private BigDecimal oneCoinPrice;

    @Column(name = "ENTIRE_VALUATION")
    private BigDecimal entireValuation;
}
