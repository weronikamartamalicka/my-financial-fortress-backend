package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelPortfolioService {
    private final GoldInvestmentService goldInvestmentService;
    private final DevelopedMarketStocksService developedMarketStocksService;
    private final EmergingMarketStocksService emergingMarketStocksService;
    private final BondsQuotedOnTheMarketService bondsQuotedOnTheMarketService;
    private final InflationIndexedBondsService inflationIndexedBondsService;
    private final ModelPortfolioRepository modelPortfolioRepository;


    public void calculateComposition(BigDecimal investmentCapital, ModelPortfolioInvestment modelPortfolio) {

        ZoneId z = ZoneId.of( "Europe/Warsaw" );
        modelPortfolio.setDate(LocalDateTime.now(z));

        goldInvestmentService.calculateGoldComposition(investmentCapital, modelPortfolio);
        developedMarketStocksService.calculateDevelopedMarketComposition(investmentCapital, modelPortfolio);
        emergingMarketStocksService.calculateEmergingMarketComposition(investmentCapital, modelPortfolio);
        bondsQuotedOnTheMarketService.calculateBondsQuotedComposition(investmentCapital, modelPortfolio);
        inflationIndexedBondsService.calculateBondsIndexedComposition(investmentCapital, modelPortfolio);
        calculatePercentageComposition(modelPortfolio);
    }

    public ModelPortfolioInvestment calculatePercentageComposition(ModelPortfolioInvestment modelPortfolio) {

        BigDecimal goldValue = modelPortfolio.getGoldValue();
        BigDecimal bondsIndexedValue = modelPortfolio.getBondsIndexedValue();
        BigDecimal bondsQuotedValue = modelPortfolio.getBondsQuotedValue();
        BigDecimal developedMarketValue = modelPortfolio.getDevelopedMarketValue();
        BigDecimal emergingMarketValue = modelPortfolio.getEmergingMarketValue();

        BigDecimal entirePortfolioValue = goldValue.add(bondsIndexedValue)
                .add(bondsQuotedValue)
                .add(developedMarketValue)
                .add(emergingMarketValue);

        BigDecimal goldPercentage = goldValue.divide(entirePortfolioValue, 2, RoundingMode.HALF_UP);
        BigDecimal bondsIndexedPercentage = bondsIndexedValue.divide(entirePortfolioValue, 2, RoundingMode.HALF_UP);
        BigDecimal bondsQuotedPercentage = bondsQuotedValue.divide(entirePortfolioValue, 2, RoundingMode.HALF_UP);
        BigDecimal developedMarketPercentage = developedMarketValue.divide(entirePortfolioValue, 2, RoundingMode.HALF_UP);
        BigDecimal emergingMarketPercentage = emergingMarketValue.divide(entirePortfolioValue, 2, RoundingMode.HALF_UP);

        modelPortfolio.setGoldPercentage(goldPercentage);
        modelPortfolio.setBondsIndexedPercentage(bondsIndexedPercentage);
        modelPortfolio.setBondsQuotedPercentage(bondsQuotedPercentage);
        modelPortfolio.setDevelopedMarketPercentage(developedMarketPercentage);
        modelPortfolio.setEmergingMarketPercentage(emergingMarketPercentage);
        modelPortfolio.setEntireValue(entirePortfolioValue);

        return modelPortfolio;
    }

    public List<ModelPortfolioInvestment> getAll() {
        return modelPortfolioRepository.findAll();
    }

    public void deleteAll() {
        modelPortfolioRepository.deleteAll();
    }

    public ModelPortfolioInvestment findTopByDate() {
        return modelPortfolioRepository.findAll().get(modelPortfolioRepository.findAll().size() - 1);
    }

    public void saveModelPortfolio(ModelPortfolioInvestment modelPortfolio) {
        modelPortfolioRepository.save(modelPortfolio);
    }

    public ModelPortfolioInvestment copyPortfolioValues(ModelPortfolioInvestment modelPortfolio) {
        ModelPortfolioInvestment lastModelPortfolio = findTopByDate();

        ZoneId z = ZoneId.of( "America/Montreal" ) ;

        modelPortfolio.setDate(LocalDateTime.now(z));
        modelPortfolio.setBondsIndexedValue(lastModelPortfolio.getBondsIndexedValue());
        modelPortfolio.setGoldValue(lastModelPortfolio.getGoldValue());
        modelPortfolio.setEmergingMarketValue(lastModelPortfolio.getEmergingMarketValue());
        modelPortfolio.setDevelopedMarketValue(lastModelPortfolio.getDevelopedMarketValue());
        modelPortfolio.setBondsQuotedValue(lastModelPortfolio.getBondsQuotedValue());

        return modelPortfolio;
    }
}
