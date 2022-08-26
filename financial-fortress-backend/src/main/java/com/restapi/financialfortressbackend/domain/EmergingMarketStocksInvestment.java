package com.restapi.financialfortressbackend.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
@Entity(name = "EMERGING_MARKET_STOCKS")
public class EmergingMarketStocksInvestment {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "TYPE")
    private static final String TYPE = "WIG5";

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "VALUATION")
    private BigDecimal valuation;

    @Column(name = "COMMISSION")
    private BigDecimal commissionRate;

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "ENTIRE_VALUATION")
    private BigDecimal entireValuation;

    public EmergingMarketStocksInvestment(BigDecimal quantity, BigDecimal valuation, BigDecimal commissionRate,
                                          LocalDateTime date, BigDecimal entireValuation) {
        this.quantity = quantity;
        this.valuation = valuation;
        this.commissionRate = commissionRate;
        this.date = date;
        this.entireValuation = entireValuation;
    }
}
