package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ModelPortfolioService {
    @Autowired
    GoldInvestmentService goldInvestmentService;
    @Autowired
    DevelopedMarketStocksService developedMarketStocksService;
    @Autowired
    EmergingMarketStocksService emergingMarketStocksService;
    @Autowired
    BondsQuotedOnTheMarketService bondsQuotedOnTheMarketService;
    @Autowired
    InflationIndexedBondsService inflationIndexedBondsService;
    @Autowired
    ModelPortfolioRepository modelPortfolioRepository;
    private ModelPortfolioInvestment modelPortfolio;

    public void calculateComposition(BigDecimal investmentCapital) {

        modelPortfolio = new ModelPortfolioInvestment();
        modelPortfolio.setDate(LocalDate.now());
        modelPortfolioRepository.save(modelPortfolio);

        goldInvestmentService.calculateGoldComposition(investmentCapital);
        developedMarketStocksService.calculateDevelopedMarketComposition(investmentCapital);
        emergingMarketStocksService.calculateEmergingMarketComposition(investmentCapital);
        bondsQuotedOnTheMarketService.calculateBondsQuotedComposition(investmentCapital);
        inflationIndexedBondsService.calculateBondsIndexedComposition(investmentCapital);
    }
}
