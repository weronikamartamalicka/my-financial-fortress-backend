package com.restapi.financialfortressbackend.domain.investment;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table
@Getter
@Setter
@Entity(name = "GOLD")
public class GoldInvestment extends SimpleInvestment {


    @Column(name = "PURCHASE_VALUATION")
    public final BigDecimal purchaseValuation = BigDecimal.valueOf(4667);

    public GoldInvestment() {
        super(InvestmentInstrumentName.GOLD.getName());
    }

    public GoldInvestment(BigDecimal quantity) {
        super(InvestmentInstrumentName.GOLD.getName(), quantity);
    }
}
