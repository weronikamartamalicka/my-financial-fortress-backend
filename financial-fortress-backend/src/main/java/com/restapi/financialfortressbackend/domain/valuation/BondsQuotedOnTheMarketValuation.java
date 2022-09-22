package com.restapi.financialfortressbackend.domain.valuation;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity(name = "BONDS_QUOTED_VALUATION")
public class BondsQuotedOnTheMarketValuation extends SimpleValuation {

    @Column(name = "COMMISSION")
    private BigDecimal commissionRate;

    public BondsQuotedOnTheMarketValuation() {
        super("10 Yr Gov Bond iShr Ix");
    }

    public BondsQuotedOnTheMarketValuation(Long id, LocalDateTime date, BigDecimal valuation, BigDecimal commissionRate) {
        super(id, date,"10 Yr Gov Bond iShr Ix", valuation);
        this.commissionRate = commissionRate;
    }
}
