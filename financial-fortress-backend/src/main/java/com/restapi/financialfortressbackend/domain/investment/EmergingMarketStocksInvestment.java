package com.restapi.financialfortressbackend.domain.investment;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "EMERGING_MARKET_STOCKS")
public class EmergingMarketStocksInvestment extends SimpleInvestment {

    public EmergingMarketStocksInvestment() {
        super("BofAML AAA-A Emerging Markets Corporate Ix");
    }

    public EmergingMarketStocksInvestment(BigDecimal quantity) {
        super("BofAML AAA-A Emerging Markets Corporate Ix", quantity);
    }
}
