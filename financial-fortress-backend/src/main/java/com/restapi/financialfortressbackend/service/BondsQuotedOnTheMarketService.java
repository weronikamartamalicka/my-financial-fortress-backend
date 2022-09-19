package com.restapi.financialfortressbackend.service;

import com.google.common.collect.Iterables;
import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketInvestment;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.repository.BondsQuotedInvestmentRepository;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

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

    public void calculateBondsQuotedComposition(BigDecimal investmentCapital, ModelPortfolioInvestment modelPortfolio) {

        BondsQuotedOnTheMarketInvestment bondsQuotedOnTheMarketInvestment = new BondsQuotedOnTheMarketInvestment();
        ZoneId z = ZoneId.of( "Europe/Warsaw" );
        bondsQuotedOnTheMarketInvestment.setDate(LocalDateTime.now(z));
        bondsQuotedOnTheMarketInvestment.setRedemptionDate(LocalDate.now().plusYears(10));

        BigDecimal saleValuation = bondsQuotedValuationService.findTopByDate().getValuation();
        BigDecimal commissionValue = bondsQuotedValuationService.findTopByDate().getCommissionRate();
        BigDecimal oneBondValuation = saleValuation;
        BigDecimal bondsQuotedModelValuation = investmentCapital.multiply(BONDS_QUOTED_PERCENTAGE);
        BigDecimal capital = bondsQuotedModelValuation.subtract(commissionValue);

        BigDecimal bondsNumber = capital.divide(oneBondValuation, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = bondsNumber.multiply(oneBondValuation);

        modelPortfolio.setBondsQuotedValue(entireStocksValuation);
        bondsQuotedOnTheMarketInvestment.setQuantity(bondsNumber);
        bondsQuotedOnTheMarketInvestment.setEntireValuation(entireStocksValuation);

        bondsQuotedInvestmentRepository.save(bondsQuotedOnTheMarketInvestment);
    }

    public BondsQuotedOnTheMarketInvestment findByType(String type) {

        return Iterables.getLast(bondsQuotedInvestmentRepository.findByType(type)
                .orElse(List.of(new BondsQuotedOnTheMarketInvestment(BigDecimal.valueOf(0)))));
    }

    public List<BondsQuotedOnTheMarketInvestment> findAll() {
        return bondsQuotedInvestmentRepository.findAll();
    }

    public void deleteAll() {
        bondsQuotedInvestmentRepository.deleteAll();
    }

    public void saveBondsQuotedInvestment(BondsQuotedOnTheMarketInvestment bondsQuotedInvestment) {
        bondsQuotedInvestmentRepository.save(bondsQuotedInvestment);
    }

    public BondsQuotedOnTheMarketInvestment findTopByDate() {
        return bondsQuotedInvestmentRepository.findAll().get(bondsQuotedInvestmentRepository.findAll().size() - 1);
    }
}
