package com.restapi.financialfortressbackend.domain.investment;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;

import javax.persistence.*;
import java.math.BigDecimal;

@Table
@Entity(name = "EMERGING_MARKET_STOCKS")
public class EmergingMarketStocksInvestment extends SimpleInvestment {

    public EmergingMarketStocksInvestment() {
        super(InvestmentInstrumentName.EMERGING_ETF.getName());
    }

    public EmergingMarketStocksInvestment(BigDecimal quantity) {
        super(InvestmentInstrumentName.EMERGING_ETF.getName(), quantity);
    }
}
