package com.restapi.financialfortressbackend.domain.investment;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;

import javax.persistence.*;
import java.math.BigDecimal;

@Table
@Entity(name = "DEVELOPED_MARKET_STOCKS")
public class DevelopedMarketStocksInvestment extends SimpleInvestment {


    public DevelopedMarketStocksInvestment() {
        super(InvestmentInstrumentName.DEVELOPED_ETF.getName());
    }

    public DevelopedMarketStocksInvestment(BigDecimal quantity) {
        super(InvestmentInstrumentName.DEVELOPED_ETF.getName(),quantity);
    }
}
