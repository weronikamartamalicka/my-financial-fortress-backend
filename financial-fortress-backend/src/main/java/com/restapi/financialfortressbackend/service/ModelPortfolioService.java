package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ModelPortfolioService {
    private final GoldInvestmentService goldInvestmentService;
    private final DevelopedMarketStocksService developedMarketStocksService;
    private final EmergingMarketStocksService emergingMarketStocksService;
    private final BondsQuotedOnTheMarketService bondsQuotedOnTheMarketService;
    private final InflationIndexedBondsService inflationIndexedBondsService;
    private final ModelPortfolioRepository modelPortfolioRepository;
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
        calculatePercentageComposition();
    }

    public void calculatePercentageComposition() {

        ModelPortfolioInvestment modelPortfolioInvestment = modelPortfolioRepository.findByDate(LocalDate.now());
        BigDecimal goldValue = modelPortfolioInvestment.getGoldValue();
        BigDecimal bondsIndexedValue = modelPortfolioInvestment.getBondsIndexedValue();
        BigDecimal bondsQuotedValue = modelPortfolioInvestment.getBondsQuotedValue();
        BigDecimal developedMarketValue = modelPortfolioInvestment.getDevelopedMarketValue();
        BigDecimal emergingMarketValue = modelPortfolioInvestment.getEmergingMarketValue();

        BigDecimal entirePortfolioValue = goldValue.add(bondsIndexedValue)
                .add(bondsQuotedValue)
                .add(developedMarketValue)
                .add(emergingMarketValue);

        BigDecimal goldPercentage = goldValue.divide(entirePortfolioValue, 2, RoundingMode.HALF_UP);
        BigDecimal bondsIndexedPercentage = bondsIndexedValue.divide(entirePortfolioValue, 2, RoundingMode.HALF_UP);
        BigDecimal bondsQuotedPercentage = bondsQuotedValue.divide(entirePortfolioValue, 2, RoundingMode.HALF_UP);
        BigDecimal developedMarketPercentage = developedMarketValue.divide(entirePortfolioValue, 2, RoundingMode.HALF_UP);
        BigDecimal emergingMarketPercentage = emergingMarketValue.divide(entirePortfolioValue, 2, RoundingMode.HALF_UP);

        modelPortfolioInvestment.setGoldPercentage(goldPercentage);
        modelPortfolioInvestment.setBondsIndexedPercentage(bondsIndexedPercentage);
        modelPortfolioInvestment.setBondsQuotedPercentage(bondsQuotedPercentage);
        modelPortfolioInvestment.setDevelopedMarketPercentage(developedMarketPercentage);
        modelPortfolioInvestment.setEmergingMarketPercentage(emergingMarketPercentage);
        modelPortfolioInvestment.setEntireValue(entirePortfolioValue);

        modelPortfolioRepository.save(modelPortfolioInvestment);
    }
}
