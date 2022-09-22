package com.restapi.financialfortressbackend.domain.valuation;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
@Table
@Getter
@Setter
@Entity(name = "EMERGING_MARKET_STOCKS_VALUATION")
public class EmergingMarketStocksValuation extends SimpleValuation {


    @Column(name = "COMMISSION")
    private BigDecimal commissionRate;

    public EmergingMarketStocksValuation() {
        super(InvestmentInstrumentName.EMERGING_ETF.getName());
    }
}
