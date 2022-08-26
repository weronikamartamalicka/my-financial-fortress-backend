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
@Entity(name = "BONDS_QUOTED_ON_THE_MARKET")
public class BondsQuotedOnTheMarketInvestment {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "TYPE")
    private static final String TYPE = "NMG7";

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "VALUATION")
    private BigDecimal valuation;

    @Column(name = "COMMISSION")
    private final static BigDecimal FACE_VALUE = new BigDecimal(1000);

    @Column(name = "COUPON")
    private BigDecimal couponRate;

    @Column(name = "REDEMPTION")
    private LocalDateTime redemptionDate;

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "INTEREST_PERIOD")
    private LocalDateTime interestPeriod;

    @Column(name = "ENTIRE_VALUATION")
    private BigDecimal entireValuation;

    public BondsQuotedOnTheMarketInvestment(BigDecimal quantity, BigDecimal valuation, BigDecimal couponRate,
                                            LocalDateTime redemptionDate, LocalDateTime date,
                                            LocalDateTime interestPeriod, BigDecimal entireValuation) {
        this.quantity = quantity;
        this.valuation = valuation;
        this.couponRate = couponRate;
        this.redemptionDate = redemptionDate;
        this.date = date;
        this.interestPeriod = interestPeriod;
        this.entireValuation = entireValuation;
    }
}
