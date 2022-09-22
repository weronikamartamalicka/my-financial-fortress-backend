package com.restapi.financialfortressbackend.domain.valuation;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "EMERGING_MARKET_STOCKS_VALUATION")
public class EmergingMarketStocksValuation extends SimpleValuation {


    @Column(name = "COMMISSION")
    private BigDecimal commissionRate;

    public EmergingMarketStocksValuation() {
        super("BofAML AAA-A Emerging Markets Corporate Ix");
    }
}
