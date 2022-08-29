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

    @Column(name = "FACE_VALUE")
    private final static BigDecimal FACE_VALUE = new BigDecimal(1000);

    @Column(name = "COUPON")
    private BigDecimal couponRate;

    @Column(name = "COMMISSION")
    private BigDecimal commissionRate;

    @Column(name = "REDEMPTION")
    private LocalDate redemptionDate;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "INTEREST_PERIOD")
    private LocalDate interestPeriod;

    @Column(name = "ENTIRE_VALUATION")
    private BigDecimal entireValuation;

    public BondsQuotedOnTheMarketInvestment(BigDecimal quantity, BigDecimal valuation, BigDecimal couponRate, BigDecimal commissionRate, LocalDate redemptionDate, LocalDate date, LocalDate interestPeriod, BigDecimal entireValuation) {
        this.quantity = quantity;
        this.valuation = valuation;
        this.couponRate = couponRate;
        this.commissionRate = commissionRate;
        this.redemptionDate = redemptionDate;
        this.date = date;
        this.interestPeriod = interestPeriod;
        this.entireValuation = entireValuation;
    }
}
