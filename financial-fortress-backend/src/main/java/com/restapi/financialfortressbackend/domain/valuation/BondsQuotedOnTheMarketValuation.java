package com.restapi.financialfortressbackend.domain.valuation;


import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table
@Getter
@Setter
@Entity(name = "BONDS_QUOTED_VALUATION")
public class BondsQuotedOnTheMarketValuation extends SimpleValuation {

    @Column(name = "COMMISSION")
    private BigDecimal commissionRate;

    public BondsQuotedOnTheMarketValuation() {
        super(InvestmentInstrumentName.BONDS_QUOTED.getName());
    }
}
