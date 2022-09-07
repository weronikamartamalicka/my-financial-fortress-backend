package com.restapi.financialfortressbackend.service;

import com.google.common.collect.Iterables;
import com.restapi.financialfortressbackend.domain.DevelopedMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.InflationIndexedBondsInvestment;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.exception.InflationIndexedNotFoundException;
import com.restapi.financialfortressbackend.repository.InflationIndexedInvestmentRepository;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InflationIndexedBondsService {

    private static final BigDecimal BONDS_INDEXED_PERCENTAGE = new BigDecimal(0.4);
    @Autowired
    InflationIndexedInvestmentRepository inflationIndexedInvestmentRepository;
    @Autowired
    InflationValuationService inflationValuationService;
    @Autowired
    ModelPortfolioRepository modelPortfolioRepository;

    public void calculateBondsIndexedComposition(BigDecimal investmentCapital, ModelPortfolioInvestment modelPortfolio) {

        InflationIndexedBondsInvestment inflationIndexedBondsInvestment = new InflationIndexedBondsInvestment();
        inflationIndexedBondsInvestment.setRedemptionDate(LocalDate.now().plusYears(12));

        BigDecimal remainingCapital = investmentCapital
                .subtract(modelPortfolioRepository.findByDate(LocalDate.now()).getBondsQuotedValue())
                .subtract(modelPortfolioRepository.findByDate(LocalDate.now()).getDevelopedMarketValue())
                .subtract(modelPortfolioRepository.findByDate(LocalDate.now()).getGoldValue())
                .subtract(modelPortfolioRepository.findByDate(LocalDate.now()).getEmergingMarketValue());

        BigDecimal price = inflationIndexedBondsInvestment.getPrice();
        BigDecimal capital = remainingCapital;

        BigDecimal bondsNumber = capital.divide(price, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = bondsNumber.multiply(price);

        modelPortfolio.setBondsIndexedValue(entireStocksValuation);
        inflationIndexedBondsInvestment.setQuantity(bondsNumber);
        inflationValuationService.findTopByDate().setValuation(entireStocksValuation);

        inflationIndexedInvestmentRepository.save(inflationIndexedBondsInvestment);
    }

    public InflationIndexedBondsInvestment findByType(String type) {
        return Iterables.getLast(inflationIndexedInvestmentRepository.findByType(type)
                .orElse(List.of(new InflationIndexedBondsInvestment(BigDecimal.valueOf(0)))));
    }

    public void deleteAll() {
        inflationIndexedInvestmentRepository.deleteAll();
    }
}
