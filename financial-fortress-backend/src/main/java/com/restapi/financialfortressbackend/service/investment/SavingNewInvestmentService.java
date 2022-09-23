package com.restapi.financialfortressbackend.service.investment;

import com.restapi.financialfortressbackend.domain.investment.BondsQuotedOnTheMarketInvestment;
import com.restapi.financialfortressbackend.domain.investment.InflationIndexedBondsInvestment;
import com.restapi.financialfortressbackend.domain.investment.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.domain.investment.SimpleInvestment;
import com.restapi.financialfortressbackend.service.ModelPortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class SavingNewInvestmentService {

    private final ModelPortfolioService modelPortfolioService;
    private final ZoneId z = ZoneId.of( "Europe/Warsaw");

    public SimpleInvestment getNewInvestment(SimpleInvestment simpleInvestment, SimpleInvestment lastInvestment,
                                              BigDecimal entireValuation) {
        simpleInvestment.setDate(LocalDateTime.now(z));
        simpleInvestment.setQuantity(lastInvestment.getQuantity());
        simpleInvestment.setEntireValuation(entireValuation);
        ModelPortfolioInvestment modelPortfolio = new ModelPortfolioInvestment();
        modelPortfolio.setDate(LocalDateTime.now(z));
        modelPortfolio = modelPortfolioService.copyPortfolioValues(modelPortfolio);
        String instrumentName = simpleInvestment.getType();

        switch(instrumentName) {
            case "ROD0934" :
                InflationIndexedBondsInvestment investment = (InflationIndexedBondsInvestment) simpleInvestment;
                InflationIndexedBondsInvestment lastInvest = (InflationIndexedBondsInvestment) lastInvestment;
                investment.setRedemptionDate(lastInvest.getRedemptionDate());
                modelPortfolio.setBondsIndexedValue(entireValuation);
                modelPortfolio = modelPortfolioService.calculatePercentageComposition(modelPortfolio);
                modelPortfolioService.saveModelPortfolio(modelPortfolio);
                return investment;
            case "10 Yr Gov Bond iShr Ix" :
                BondsQuotedOnTheMarketInvestment investment1 = (BondsQuotedOnTheMarketInvestment) simpleInvestment;
                BondsQuotedOnTheMarketInvestment lastInvest1 = (BondsQuotedOnTheMarketInvestment) lastInvestment;
                investment1.setRedemptionDate(lastInvest1.getRedemptionDate());
                investment1.setFACE_VALUE(BigDecimal.valueOf(1000));
                modelPortfolio.setBondsQuotedValue(entireValuation);
                modelPortfolio = modelPortfolioService.calculatePercentageComposition(modelPortfolio);
                modelPortfolioService.saveModelPortfolio(modelPortfolio);
                return investment1;
            case "MSCI China A DivAdj Ix" :
                modelPortfolio.setDevelopedMarketValue(entireValuation);
                modelPortfolio = modelPortfolioService.calculatePercentageComposition(modelPortfolio);
                modelPortfolioService.saveModelPortfolio(modelPortfolio);
                break;
            case "BofAML AAA-A Emerging Markets Corporate Ix" :
                modelPortfolio.setEmergingMarketValue(entireValuation);
                modelPortfolio = modelPortfolioService.calculatePercentageComposition(modelPortfolio);
                modelPortfolioService.saveModelPortfolio(modelPortfolio);
                break;
            case "Krugerrand 1/2 oz." :
                modelPortfolio.setGoldValue(entireValuation);
                modelPortfolio = modelPortfolioService.calculatePercentageComposition(modelPortfolio);
                modelPortfolioService.saveModelPortfolio(modelPortfolio);
                break;
        }

        return simpleInvestment;
    }
}
