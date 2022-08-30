package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.*;
import com.restapi.financialfortressbackend.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

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
        goldValuation.setDate(LocalDate.now());
        goldValuation.setPurchaseValuation(new BigDecimal(600));
        goldValuation.setSaleValuation(new BigDecimal(700));

        InflationIndexedBondsValuation inflationIndexedBondsValuation = new InflationIndexedBondsValuation();
        inflationIndexedBondsValuation.setDate(LocalDate.now());
        inflationIndexedBondsValuation.setCommissionRate(new BigDecimal(50));
        inflationIndexedBondsValuation.setPrice(new BigDecimal(100));

        BondsQuotedOnTheMarketValuation bondsQuotedOnTheMarketValuation = new BondsQuotedOnTheMarketValuation();
        bondsQuotedOnTheMarketValuation.setDate(LocalDate.now());
        bondsQuotedOnTheMarketValuation.setValuation(new BigDecimal(110));
        bondsQuotedOnTheMarketValuation.setCommissionRate(new BigDecimal(30));

        EmergingMarketStocksValuation emergingMarketStocksValuation = new EmergingMarketStocksValuation();
        emergingMarketStocksValuation.setCommissionRate(new BigDecimal(50));
        emergingMarketStocksValuation.setDate(LocalDate.now());
        emergingMarketStocksValuation.setValuation(new BigDecimal(12.5));

        DevelopedMarketStocksValuation developedMarketStocksValuation = new DevelopedMarketStocksValuation();
        developedMarketStocksValuation.setCommissionRate(new BigDecimal(30));
        developedMarketStocksValuation.setDate(LocalDate.now());
        developedMarketStocksValuation.setValuation(new BigDecimal(25));

        goldValuationRepository.save(goldValuation);
        inflationValuationRepository.save(inflationIndexedBondsValuation);
        bondsQuotedValuationRepository.save(bondsQuotedOnTheMarketValuation);
        emergingMarketValuationRepository.save(emergingMarketStocksValuation);
        developedMarketValuationRepository.save(developedMarketStocksValuation);
    }

    @Test
    public void calculateCompositionTest() {

        modelPortfolioService.calculateComposition(new BigDecimal(50000));

        ModelPortfolioInvestment myModelPortfolio = modelPortfolioRepository.findByDate(LocalDate.now());

        System.out.println(myModelPortfolio.getBondsIndexedPercentage());
        System.out.println(myModelPortfolio.getBondsQuotedPercentage());
        System.out.println(myModelPortfolio.getDevelopedMarketPercentage());
        System.out.println(myModelPortfolio.getGoldPercentage());
        System.out.println(myModelPortfolio.getEmergingMarketPercentage());
        System.out.println(myModelPortfolio.getDate());

        try {
            goldValuationRepository.deleteAll();
            inflationValuationRepository.deleteAll();
            bondsQuotedValuationRepository.deleteAll();
            emergingMarketValuationRepository.deleteAll();
            developedMarketValuationRepository.deleteAll();

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}