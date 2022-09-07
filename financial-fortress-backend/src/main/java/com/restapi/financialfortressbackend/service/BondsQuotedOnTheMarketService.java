package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketInvestment;
import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketValuation;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.repository.BondsQuotedInvestmentRepository;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BondsQuotedOnTheMarketService {

    private static final BigDecimal BONDS_QUOTED_PERCENTAGE = new BigDecimal(0.2);
    private final static BigDecimal FACE_VALUE = new BigDecimal(1000);
    @Autowired
    BondsQuotedInvestmentRepository bondsQuotedInvestmentRepository;
    @Autowired
    BondsQuotedValuationService bondsQuotedValuationService;
    @Autowired
    ModelPortfolioRepository modelPortfolioRepository;

    public void calculateBondsQuotedComposition(BigDecimal investmentCapital) {

        BondsQuotedOnTheMarketInvestment bondsQuotedOnTheMarketInvestment = new BondsQuotedOnTheMarketInvestment();
        bondsQuotedOnTheMarketInvestment.setRedemptionDate(LocalDate.now().plusYears(10));
        ModelPortfolioInvestment myModelPortfolio = modelPortfolioRepository.findByDate(LocalDate.now());

        BigDecimal saleValuation = bondsQuotedValuationService.findByDate(LocalDate.now()).getValuation();
        BigDecimal commissionValue = bondsQuotedValuationService.findByDate(LocalDate.now()).getCommissionRate();
        BigDecimal oneBondValuation = saleValuation;
        BigDecimal bondsQuotedModelValuation = investmentCapital.multiply(BONDS_QUOTED_PERCENTAGE);
        BigDecimal capital = bondsQuotedModelValuation.subtract(commissionValue);

        BigDecimal bondsNumber = capital.divide(oneBondValuation, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = bondsNumber.multiply(oneBondValuation);

        myModelPortfolio.setBondsQuotedValue(entireStocksValuation);
        bondsQuotedOnTheMarketInvestment.setQuantity(bondsNumber);
        bondsQuotedValuationService.findByDate(LocalDate.now()).setEntireValuation(entireStocksValuation);

        bondsQuotedInvestmentRepository.save(bondsQuotedOnTheMarketInvestment);
        modelPortfolioRepository.save(myModelPortfolio);
    }

    public Optional<BondsQuotedOnTheMarketInvestment> findByType(String type) {
        return bondsQuotedInvestmentRepository.findByType(type);
    }

    public List<BondsQuotedOnTheMarketInvestment> findAll() {
        return bondsQuotedInvestmentRepository.findAll();
    }

    public void deleteAll() {
        bondsQuotedInvestmentRepository.deleteAll();
    }
}
