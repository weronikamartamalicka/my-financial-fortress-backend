package com.restapi.financialfortressbackend.domain.investment;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "GOLD")
public class GoldInvestment extends SimpleInvestment {


    @Column(name = "PURCHASE_VALUATION")
    public final BigDecimal purchaseValuation = BigDecimal.valueOf(4667);

    public GoldInvestment() {
        super("Krugerrand 1/2 oz.");
    }

    public GoldInvestment(BigDecimal quantity) {
        super("Krugerrand 1/2 oz.", quantity);
    }
}
