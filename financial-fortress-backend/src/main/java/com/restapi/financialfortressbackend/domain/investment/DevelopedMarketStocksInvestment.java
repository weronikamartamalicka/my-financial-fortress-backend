package com.restapi.financialfortressbackend.domain.investment;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "DEVELOPED_MARKET_STOCKS")
public class DevelopedMarketStocksInvestment extends SimpleInvestment {


    public DevelopedMarketStocksInvestment() {
        super("MSCI China A DivAdj Ix");
    }

    public DevelopedMarketStocksInvestment(BigDecimal quantity) {
        super("MSCI China A DivAdj Ix",quantity);
    }
}
