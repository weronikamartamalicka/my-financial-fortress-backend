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
import java.util.Optional;

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

        BigDecimal saleValuation = emergingMarketValuationService.findTopByDate().getValuation();
        BigDecimal commissionValue = emergingMarketValuationService.findTopByDate().getCommissionRate();
        BigDecimal emergingMarketModelValuation = investmentCapital.multiply(EMERGING_MARKET_PERCENTAGE);
        BigDecimal capital = emergingMarketModelValuation.subtract(commissionValue);

        BigDecimal stocksNumber = capital.divide(saleValuation, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = stocksNumber.multiply(saleValuation);

        myModelPortfolio.setEmergingMarketValue(entireStocksValuation);
        emergingMarketStocksInvestment.setQuantity(stocksNumber);
        emergingMarketValuationService.findTopByDate().setEntireValuation(entireStocksValuation);

        emergingMarketRepository.save(emergingMarketStocksInvestment);
        modelPortfolioRepository.save(myModelPortfolio);
    }

    public Optional<EmergingMarketStocksInvestment> findByType(String type) {
        return emergingMarketRepository.findByType(type);
    }

    public void deleteAll() {
        emergingMarketRepository.deleteAll();
    }
}
