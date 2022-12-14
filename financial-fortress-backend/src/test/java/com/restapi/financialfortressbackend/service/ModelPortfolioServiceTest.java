package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.investment.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.domain.valuation.*;
import com.restapi.financialfortressbackend.repository.*;
import com.restapi.financialfortressbackend.repository.valuation.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ModelPortfolioServiceTest {

    @Autowired
    ModelPortfolioService modelPortfolioService;
    @Autowired
    ModelPortfolioRepository modelPortfolioRepository;
    @Autowired
    GoldValuationRepository goldValuationRepository;
    @Autowired
    BondsQuotedValuationRepository bondsQuotedValuationRepository;
    @Autowired
    InflationValuationRepository inflationValuationRepository;
    @Autowired
    EmergingMarketValuationRepository emergingMarketValuationRepository;
    @Autowired
    DevelopedMarketValuationRepository developedMarketValuationRepository;

    @BeforeEach
    public void prepareData() {

        GoldValuation goldValuation = new GoldValuation();
        goldValuation.setDate(LocalDateTime.now());
        goldValuation.setValuation(new BigDecimal(6000));

        InflationIndexedBondsValuation inflationIndexedBondsValuation = new InflationIndexedBondsValuation();
        inflationIndexedBondsValuation.setDate(LocalDateTime.now());
        inflationIndexedBondsValuation.setValuation(new BigDecimal(100));

        BondsQuotedOnTheMarketValuation bondsQuotedOnTheMarketValuation = new BondsQuotedOnTheMarketValuation();
        bondsQuotedOnTheMarketValuation.setDate(LocalDateTime.now());
        bondsQuotedOnTheMarketValuation.setValuation(new BigDecimal(110));
        bondsQuotedOnTheMarketValuation.setCommissionRate(new BigDecimal(30));

        EmergingMarketStocksValuation emergingMarketStocksValuation = new EmergingMarketStocksValuation();
        emergingMarketStocksValuation.setCommissionRate(new BigDecimal(50));
        emergingMarketStocksValuation.setDate(LocalDateTime.now());
        emergingMarketStocksValuation.setValuation(new BigDecimal(12.5));

        DevelopedMarketStocksValuation developedMarketStocksValuation = new DevelopedMarketStocksValuation();
        developedMarketStocksValuation.setCommissionRate(new BigDecimal(30));
        developedMarketStocksValuation.setDate(LocalDateTime.now());
        developedMarketStocksValuation.setValuation(new BigDecimal(25));

        goldValuationRepository.save(goldValuation);
        inflationValuationRepository.save(inflationIndexedBondsValuation);
        bondsQuotedValuationRepository.save(bondsQuotedOnTheMarketValuation);
        emergingMarketValuationRepository.save(emergingMarketStocksValuation);
        developedMarketValuationRepository.save(developedMarketStocksValuation);
    }

    @Test
    public void calculateCompositionTest() {

        ModelPortfolioInvestment modelPortfolioInvestment = new ModelPortfolioInvestment();

        modelPortfolioService.calculateComposition(new BigDecimal(50000), modelPortfolioInvestment);
        modelPortfolioRepository.save(modelPortfolioInvestment);

        ModelPortfolioInvestment myModelPortfolio = modelPortfolioService.findTopByDate();

        BigDecimal bondsIndexed = myModelPortfolio.getBondsIndexedPercentage();
        BigDecimal bondsQuoted = myModelPortfolio.getBondsQuotedPercentage();
        BigDecimal developedMarket = myModelPortfolio.getDevelopedMarketPercentage();
        BigDecimal gold = myModelPortfolio.getGoldPercentage();
        BigDecimal emergingMarket = myModelPortfolio.getEmergingMarketPercentage();

        assertEquals(BigDecimal.valueOf(0.38), bondsIndexed);
        assertEquals(BigDecimal.valueOf(0.15), developedMarket);


        try {
            goldValuationRepository.deleteAll();
            inflationValuationRepository.deleteAll();
            bondsQuotedValuationRepository.deleteAll();
            emergingMarketValuationRepository.deleteAll();
            developedMarketValuationRepository.deleteAll();
            modelPortfolioRepository.deleteAll();

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}