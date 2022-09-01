package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.GoldInvestment;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.exception.GoldNotFoundException;
import com.restapi.financialfortressbackend.repository.GoldInvestmentRepository;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class GoldInvestmentService {

    private static final BigDecimal GOLD_PERCENTAGE = new BigDecimal(0.1);
    @Autowired
    GoldInvestmentRepository goldRepository;
    @Autowired
    GoldValuationService goldValuationService;
    @Autowired
    ModelPortfolioRepository modelPortfolioRepository;

    public void calculateGoldComposition(BigDecimal investmentCapital) {

        GoldInvestment goldInvestment = new GoldInvestment();

        BigDecimal goldSale = goldValuationService.findByDate(LocalDate.now()).getSaleValuation();
        BigDecimal goldPurchase = goldValuationService.findByDate(LocalDate.now()).getPurchaseValuation();
        BigDecimal goldModelValuation = investmentCapital.multiply(GOLD_PERCENTAGE);
        ModelPortfolioInvestment myModelPortfolio = modelPortfolioRepository.findByDate(LocalDate.now());

        if(goldModelValuation.compareTo(goldSale) == -1) {
            myModelPortfolio.setGoldValue(goldSale);
            goldInvestment.setQuantity(new BigDecimal(1));
            goldValuationService.findByDate(LocalDate.now()).setEntireValuation(goldPurchase);

        } else {
            BigDecimal coinsQuantity = goldModelValuation.divide(goldSale, 0, RoundingMode.DOWN);
            goldInvestment.setQuantity(coinsQuantity);
            BigDecimal actualValue = coinsQuantity.multiply(goldPurchase);
            goldValuationService.findByDate(LocalDate.now()).setEntireValuation(actualValue);
            myModelPortfolio.setGoldValue(actualValue);
        }

        goldRepository.save(goldInvestment);
        modelPortfolioRepository.save(myModelPortfolio);
    }
}
