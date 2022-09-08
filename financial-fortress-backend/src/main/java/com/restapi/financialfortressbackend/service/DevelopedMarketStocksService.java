package com.restapi.financialfortressbackend.service;

import com.google.common.collect.Iterables;
import com.restapi.financialfortressbackend.domain.DevelopedMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.repository.DevelopedMarketInvestmentRepository;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class DevelopedMarketStocksService {

    private static final BigDecimal DEVELOPED_MARKET_PERCENTAGE = new BigDecimal(0.15);
    @Autowired
    DevelopedMarketValuationService developedMarketValuationService;
    @Autowired
    ModelPortfolioRepository modelPortfolioRepository;
    @Autowired
    DevelopedMarketInvestmentRepository developedMarketRepository;

    public void calculateDevelopedMarketComposition(BigDecimal investmentCapital, ModelPortfolioInvestment modelPortfolio) {

        DevelopedMarketStocksInvestment developedMarketStocksInvestment = new DevelopedMarketStocksInvestment();

        BigDecimal saleValuation = developedMarketValuationService.findTopByDate().getValuation();
        BigDecimal commissionValue = developedMarketValuationService.findTopByDate().getCommissionRate();
        BigDecimal developedMarketModelValuation = investmentCapital.multiply(DEVELOPED_MARKET_PERCENTAGE);
        BigDecimal capital = developedMarketModelValuation.subtract(commissionValue);

        BigDecimal stocksNumber = capital.divide(saleValuation, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = stocksNumber.multiply(saleValuation);

        modelPortfolio.setDevelopedMarketValue(entireStocksValuation);
        developedMarketStocksInvestment.setQuantity(stocksNumber);
        developedMarketStocksInvestment.setEntireValuation(entireStocksValuation);

        developedMarketRepository.save(developedMarketStocksInvestment);
    }

    public DevelopedMarketStocksInvestment findByType(String type) {
        return Iterables.getLast(developedMarketRepository.findByType(type)
                .orElse(List.of(new DevelopedMarketStocksInvestment(BigDecimal.valueOf(0)))));
    }

    public void deleteAll() {
        developedMarketRepository.deleteAll();
    }

    public List<DevelopedMarketStocksInvestment> findAll() {
        return developedMarketRepository.findAll();
    }

    public DevelopedMarketStocksInvestment findTopByDate() {
        return developedMarketRepository.findAll().get(developedMarketRepository.findAll().size() - 1);
    }

    public void saveDevelopedMarketInvestment(DevelopedMarketStocksInvestment developedMarketInvestment) {
        developedMarketRepository.save(developedMarketInvestment);
    }
}
