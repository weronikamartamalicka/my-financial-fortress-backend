package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.exception.*;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ModelPortfolioServiceTest {

    @Autowired
    ModelPortfolioService modelPortfolioService;
    @Autowired
    ModelPortfolioRepository modelPortfolioRepository;

    @Test
    public void calculateCompositionTest() throws EmergingMarketStocksNotFoundException,
            DevelopedMarketStocksNotFoundException, InflationIndexedNotFoundException,
            BondsQuotedNotFoundException, GoldNotFoundException, ModelPortfolioNotFoundException {

        modelPortfolioService.calculateComposition(new BigDecimal(50000));

        ModelPortfolioInvestment myModelPortfolio = modelPortfolioRepository.findByDate(LocalDate.now()).orElseThrow(ModelPortfolioNotFoundException::new);

        System.out.println(myModelPortfolio.getBondsIndexedPercentage());
        System.out.println(myModelPortfolio.getBondsQuotedPercentage());
        System.out.println(myModelPortfolio.getDevelopedMarketPercentage());
        System.out.println(myModelPortfolio.getGoldPercentage());
        System.out.println(myModelPortfolio.getEmergingMarketPercentage());
    }

}