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
    public static final String TYPE = "NMG7";

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "FACE_VALUE")
    private final static BigDecimal FACE_VALUE = new BigDecimal(1000);

    @Column(name = "COUPON")
    private BigDecimal couponRate;

    @Column(name = "REDEMPTION")
    private LocalDate redemptionDate;

    @Column(name = "INTEREST_PERIOD")
    private LocalDate interestPeriod;

    public BondsQuotedOnTheMarketInvestment(BigDecimal quantity, BigDecimal couponRate,
                                            LocalDate redemptionDate,
                                            LocalDate interestPeriod) {
        this.quantity = quantity;
        this.couponRate = couponRate;
        this.redemptionDate = redemptionDate;
        this.interestPeriod = interestPeriod;
    }
}
