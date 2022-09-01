package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.DevelopedMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.repository.DevelopedMarketInvestmentRepository;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class DevelopedMarketStocksService {

    private static final BigDecimal DEVELOPED_MARKET_PERCENTAGE = new BigDecimal(0.15);
    @Autowired
    DevelopedMarketValuationService developedMarketValuationService;
    @Autowired
    ModelPortfolioRepository modelPortfolioRepository;
    @Autowired
    DevelopedMarketInvestmentRepository developedMarketRepository;

    public void calculateDevelopedMarketComposition(BigDecimal investmentCapital) {

        DevelopedMarketStocksInvestment developedMarketStocksInvestment = new DevelopedMarketStocksInvestment();
        ModelPortfolioInvestment myModelPortfolio = modelPortfolioRepository.findByDate(LocalDate.now());

        BigDecimal saleValuation = developedMarketValuationService.findByDate(LocalDate.now()).getValuation();
        BigDecimal commissionValue = developedMarketValuationService.findByDate(LocalDate.now()).getCommissionRate();
        BigDecimal developedMarketModelValuation = investmentCapital.subtract(commissionValue).multiply(DEVELOPED_MARKET_PERCENTAGE);
        BigDecimal capital = developedMarketModelValuation.subtract(commissionValue);

        BigDecimal stocksNumber = capital.divide(saleValuation, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = stocksNumber.multiply(saleValuation);

        myModelPortfolio.setDevelopedMarketValue(entireStocksValuation);
        developedMarketStocksInvestment.setQuantity(stocksNumber);
        developedMarketValuationService.findByDate(LocalDate.now()).setEntireValuation(entireStocksValuation);

        developedMarketRepository.save(developedMarketStocksInvestment);
        modelPortfolioRepository.save(myModelPortfolio);
    }
}
