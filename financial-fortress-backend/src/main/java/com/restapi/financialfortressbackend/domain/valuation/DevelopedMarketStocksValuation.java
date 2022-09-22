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
@Entity(name = "DEVELOPED_MARKET_STOCKS_VALUATION")
public class DevelopedMarketStocksValuation extends SimpleValuation {


    @Column(name = "COMMISSION")
    private BigDecimal commissionRate;

    public DevelopedMarketStocksValuation() {
        super("MSCI China A DivAdj Ix");
    }
}
