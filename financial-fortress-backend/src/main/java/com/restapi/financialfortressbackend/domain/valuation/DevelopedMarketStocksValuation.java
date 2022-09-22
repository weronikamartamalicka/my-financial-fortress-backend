package com.restapi.financialfortressbackend.domain.valuation;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
@Table
@Getter
@Setter
@Entity(name = "DEVELOPED_MARKET_STOCKS_VALUATION")
public class DevelopedMarketStocksValuation extends SimpleValuation {


    @Column(name = "COMMISSION")
    private BigDecimal commissionRate;

    public DevelopedMarketStocksValuation() {
        super(InvestmentInstrumentName.DEVELOPED_ETF.getName());
    }
}
