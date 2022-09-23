package com.restapi.financialfortressbackend.domain.investment;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Table
@Getter
@Setter
@Entity(name = "BONDS_QUOTED_ON_THE_MARKET")
public class BondsQuotedOnTheMarketInvestment extends SimpleInvestment {

    @Column(name = "FACE_VALUE")
    private BigDecimal FACE_VALUE = BigDecimal.valueOf(1000);

    @Column(name = "COUPON")
    private BigDecimal couponRate;

    @Column(name = "REDEMPTION")
    private LocalDate redemptionDate;

    @Column(name = "INTEREST_PERIOD")
    private BigDecimal interestPeriod;

    public BondsQuotedOnTheMarketInvestment() {
        super(InvestmentInstrumentName.BONDS_QUOTED.getName());
    }

    public BondsQuotedOnTheMarketInvestment(BigDecimal quantity) {
        super(InvestmentInstrumentName.BONDS_QUOTED.getName(), quantity);
    }

    public BondsQuotedOnTheMarketInvestment(BigDecimal quantity,
                                            BigDecimal faceValue,
                                            BigDecimal couponRate,
                                            LocalDate redemptionDate,
                                            BigDecimal interestPeriod) {
        super(InvestmentInstrumentName.BONDS_QUOTED.getName(), quantity);
        this.FACE_VALUE = faceValue;
        this.couponRate = couponRate;
        this.redemptionDate = redemptionDate;
        this.interestPeriod = interestPeriod;
    }

}
