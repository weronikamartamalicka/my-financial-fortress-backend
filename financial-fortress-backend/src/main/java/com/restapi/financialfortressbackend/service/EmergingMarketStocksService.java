package com.restapi.financialfortressbackend.service;

import com.google.common.collect.Iterables;
import com.restapi.financialfortressbackend.domain.EmergingMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.repository.EmergingMarketInvestmentRepository;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class EmergingMarketStocksService {
    private static final BigDecimal EMERGING_MARKET_PERCENTAGE = new BigDecimal(0.15);
    @Autowired
    EmergingMarketInvestmentRepository emergingMarketRepository;
    @Autowired
    ModelPortfolioRepository modelPortfolioRepository;
    @Autowired
    EmergingMarketValuationService emergingMarketValuationService;

    public void calculateEmergingMarketComposition(BigDecimal investmentCapital, ModelPortfolioInvestment modelPortfolio) {

        EmergingMarketStocksInvestment emergingMarketStocksInvestment = new EmergingMarketStocksInvestment();
        ZoneId z = ZoneId.of( "Europe/Warsaw" );
        emergingMarketStocksInvestment.setDate(LocalDateTime.now(z));

        BigDecimal saleValuation = emergingMarketValuationService.findTopByDate().getValuation();
        BigDecimal commissionValue = emergingMarketValuationService.findTopByDate().getCommissionRate();
        BigDecimal emergingMarketModelValuation = investmentCapital.multiply(EMERGING_MARKET_PERCENTAGE);
        BigDecimal capital = emergingMarketModelValuation.subtract(commissionValue);

        BigDecimal stocksNumber = capital.divide(saleValuation, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = stocksNumber.multiply(saleValuation);

        modelPortfolio.setEmergingMarketValue(entireStocksValuation);
        emergingMarketStocksInvestment.setQuantity(stocksNumber);
        emergingMarketStocksInvestment.setEntireValuation(entireStocksValuation);

        emergingMarketRepository.save(emergingMarketStocksInvestment);
    }

    public EmergingMarketStocksInvestment findByType(String type) {
        return Iterables.getLast(emergingMarketRepository.findByType(type)
                .orElse(List.of(new EmergingMarketStocksInvestment(BigDecimal.valueOf(0)))));
    }

    public void deleteAll() {
        emergingMarketRepository.deleteAll();
    }

    public List<EmergingMarketStocksInvestment> findAll() {
        return emergingMarketRepository.findAll();
    }

    public EmergingMarketStocksInvestment findTopByDate() {
        return emergingMarketRepository.findAll().get(emergingMarketRepository.findAll().size() - 1);
    }

    public void saveEmergingMarketInvestment(EmergingMarketStocksInvestment emergingMarketInvestment) {
        emergingMarketRepository.save(emergingMarketInvestment);
    }
}
