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
@Entity(name = "DEVELOPED_MARKET_STOCKS")
public class DevelopedMarketStocksInvestment {

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
    private LocalDate date;

    @Column(name = "ENTIRE_VALUATION")
    private BigDecimal entireValuation;

    public DevelopedMarketStocksInvestment(BigDecimal quantity, BigDecimal valuation, BigDecimal commissionRate,
                                           LocalDate date, BigDecimal entireValuation) {
        this.quantity = quantity;
        this.valuation = valuation;
        this.commissionRate = commissionRate;
        this.date = date;
        this.entireValuation = entireValuation;
    }
}
