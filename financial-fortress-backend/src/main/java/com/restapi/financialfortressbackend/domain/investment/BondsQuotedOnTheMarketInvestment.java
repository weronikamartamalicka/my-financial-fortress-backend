package com.restapi.financialfortressbackend.domain.investment;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "BONDS_QUOTED_ON_THE_MARKET")
public class BondsQuotedOnTheMarketInvestment extends SimpleInvestment {

    @Column(name = "FACE_VALUE")
    public final BigDecimal FACE_VALUE = BigDecimal.valueOf(1000);

    @Column(name = "COUPON")
    private BigDecimal couponRate;

    @Column(name = "REDEMPTION")
    private LocalDate redemptionDate;

    @Column(name = "INTEREST_PERIOD")
    private BigDecimal interestPeriod;

    public BondsQuotedOnTheMarketInvestment() {
        super("10 Yr Gov Bond iShr Ix");
    }

    public BondsQuotedOnTheMarketInvestment(BigDecimal quantity) {
        super("10 Yr Gov Bond iShr Ix", quantity);
    }

    public BondsQuotedOnTheMarketInvestment(BigDecimal quantity,
                                            BigDecimal couponRate,
                                            LocalDate redemptionDate,
                                            BigDecimal interestPeriod) {
        super("10 Yr Gov Bond iShr Ix", quantity);
        this.couponRate = couponRate;
        this.redemptionDate = redemptionDate;
        this.interestPeriod = interestPeriod;
    }
}
