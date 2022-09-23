package com.restapi.financialfortressbackend.service.valuation;

import com.restapi.financialfortressbackend.domain.valuation.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class SavingNewValuationsService {
    private final ZoneId z = ZoneId.of( "Europe/Warsaw");


    public SimpleValuation getNewValue(SimpleValuation simpleValuation, BigDecimal... values) {
        simpleValuation.setDate(LocalDateTime.now(z));
        BigDecimal actualValue = values[0];
        simpleValuation.setValuation(actualValue);

        String instrumentName = simpleValuation.getType();
        switch(instrumentName) {
            case "ROD0934" :
                InflationIndexedBondsValuation valuation = (InflationIndexedBondsValuation) simpleValuation;
                valuation.setInterestsValuation(BigDecimal.valueOf(0));
                return valuation;
            case "10 Yr Gov Bond iShr Ix" :
                BondsQuotedOnTheMarketValuation valuation4 = (BondsQuotedOnTheMarketValuation) simpleValuation;
                BigDecimal commissionRate2 = values[1];
                valuation4.setCommissionRate(commissionRate2);
                return valuation4;
            case "MSCI China A DivAdj Ix" :
                DevelopedMarketStocksValuation valuation1 = (DevelopedMarketStocksValuation) simpleValuation;
                BigDecimal commissionRate = values[1];
                valuation1.setCommissionRate(commissionRate);
                return valuation1;
            case "BofAML AAA-A Emerging Markets Corporate Ix" :
                EmergingMarketStocksValuation valuation2 = (EmergingMarketStocksValuation) simpleValuation;
                BigDecimal commissionRate1 = values[1];
                valuation2.setCommissionRate(commissionRate1);
                return valuation2;
            case "Krugerrand 1/2 oz." :
                GoldValuation valuation3 = (GoldValuation) simpleValuation;
                return valuation3;
        }
        return simpleValuation;
    }
}
