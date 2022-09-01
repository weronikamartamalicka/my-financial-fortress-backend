package com.restapi.financialfortressbackend.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "GOLD_VALUATION")
public class GoldValuation {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "INVESTMENT_ID", referencedColumnName = "ID")
    private GoldInvestment goldInvestment;

    @Column(name = "DATE", unique = true)
    private LocalDate date;

    @Column(name = "TYPE")
    private static final String TYPE = "Krugerrand 1/2 oz.";

    @Column(name = "SALE_VALUATION")
    private BigDecimal saleValuation;

    @Column(name = "PURCHASE_VALUATION")
    private BigDecimal purchaseValuation;

    @Column(name = "ENTIRE_VALUATION")
    private BigDecimal entireValuation;
}
