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
@Entity(name = "BONDS_QUOTED_VALUATION")
public class BondsQuotedOnTheMarketValuation {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "INVESTMENT_ID", referencedColumnName = "ID")
    private BondsQuotedOnTheMarketInvestment bondsQuotedOnTheMarketInvestment;

    @Column(name = "DATE", unique = true)
    private LocalDate date;

    @Column(name = "TYPE")
    private static final String TYPE = "NMG7";

    @Column(name = "VALUATION")
    private BigDecimal valuation;

    @Column(name = "COMMISSION")
    private BigDecimal commissionRate;

    @Column(name = "ENTIRE_VALUATION")
    private BigDecimal entireValuation;
}
