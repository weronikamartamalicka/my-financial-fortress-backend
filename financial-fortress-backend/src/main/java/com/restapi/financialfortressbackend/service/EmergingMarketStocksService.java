package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.EmergingMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.repository.EmergingMarketInvestmentRepository;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class EmergingMarketStocksService {
    private static final BigDecimal EMERGING_MARKET_PERCENTAGE = new BigDecimal(0.15);
    @Autowired
    EmergingMarketInvestmentRepository emergingMarketRepository;
    @Autowired
    ModelPortfolioRepository modelPortfolioRepository;
    @Autowired
    EmergingMarketValuationService emergingMarketValuationService;

    public void calculateEmergingMarketComposition(BigDecimal investmentCapital) {

        EmergingMarketStocksInvestment emergingMarketStocksInvestment = new EmergingMarketStocksInvestment();
        ModelPortfolioInvestment myModelPortfolio = modelPortfolioRepository.findByDate(LocalDate.now());

        BigDecimal saleValuation = emergingMarketValuationService.findByDate(LocalDate.now()).getValuation();
        BigDecimal commissionValue = emergingMarketValuationService.findByDate(LocalDate.now()).getCommissionRate();
        BigDecimal emergingMarketModelValuation = investmentCapital.multiply(EMERGING_MARKET_PERCENTAGE);
        BigDecimal capital = emergingMarketModelValuation.subtract(commissionValue);

        BigDecimal stocksNumber = capital.divide(saleValuation, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = stocksNumber.multiply(saleValuation);
        //BigDecimal actualPercentage = entireStocksValuation.divide(investmentCapital, 2, RoundingMode.HALF_UP);

        myModelPortfolio.setEmergingMarketValue(entireStocksValuation);
        //myModelPortfolio.setEmergingMarketPercentage(actualPercentage);
        emergingMarketStocksInvestment.setQuantity(stocksNumber);
        emergingMarketValuationService.findByDate(LocalDate.now()).setEntireValuation(entireStocksValuation);

        emergingMarketRepository.save(emergingMarketStocksInvestment);
        modelPortfolioRepository.save(myModelPortfolio);
    }
}
