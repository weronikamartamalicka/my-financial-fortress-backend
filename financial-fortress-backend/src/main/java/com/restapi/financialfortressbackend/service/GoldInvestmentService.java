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
import java.util.Optional;

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

        Optional<BigDecimal> goldSale = Optional.ofNullable(
                goldValuationService.findByDate(LocalDate.now()).getOneCoinPrice());

        goldSale.orElse(
                goldValuationService.findByDate(LocalDate.now().minusDays(1)).getOneCoinPrice());

        BigDecimal goldPurchase = GoldInvestment.purchaseValuation;
        BigDecimal goldModelValuation = investmentCapital.multiply(GOLD_PERCENTAGE);
        ModelPortfolioInvestment myModelPortfolio = modelPortfolioRepository.findByDate(LocalDate.now());

        if (goldModelValuation.compareTo(goldPurchase) == -1) {
            myModelPortfolio.setGoldValue(goldPurchase);
            goldInvestment.setQuantity(new BigDecimal(1));
            goldValuationService.findByDate(LocalDate.now()).setEntireValuation(goldSale.get());

        } else {
            BigDecimal coinsQuantity = goldModelValuation.divide(goldPurchase, 0, RoundingMode.DOWN);
            goldInvestment.setQuantity(coinsQuantity);
            BigDecimal actualValue = coinsQuantity.multiply(goldSale.get());
            goldValuationService.findByDate(LocalDate.now()).setEntireValuation(actualValue);
            myModelPortfolio.setGoldValue(actualValue);
        }

        goldRepository.save(goldInvestment);
        modelPortfolioRepository.save(myModelPortfolio);
    }

    public GoldInvestment findByType(String type) {
        return goldRepository.findByType(type);
    }

}
