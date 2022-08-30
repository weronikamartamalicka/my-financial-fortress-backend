package com.restapi.financialfortressbackend.service;

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

@Service
public class InflationIndexedBondsService {
    private static final BigDecimal BONDS_INDEXED_PERCENTAGE = new BigDecimal(0.4);
    @Autowired
    InflationIndexedInvestmentRepository inflationIndexedInvestmentRepository;
    @Autowired
    InflationValuationService inflationValuationService;
    @Autowired
    ModelPortfolioRepository modelPortfolioRepository;

    public void calculateBondsIndexedComposition(BigDecimal investmentCapital) {

        InflationIndexedBondsInvestment inflationIndexedBondsInvestment = new InflationIndexedBondsInvestment();
        ModelPortfolioInvestment myModelPortfolio = modelPortfolioRepository.findByDate(LocalDate.now());

        BigDecimal remainingCapital = investmentCapital
                .subtract(modelPortfolioRepository.findByDate(LocalDate.now()).getBondsQuotedValue())
                .subtract(modelPortfolioRepository.findByDate(LocalDate.now()).getDevelopedMarketValue())
                .subtract(modelPortfolioRepository.findByDate(LocalDate.now()).getGoldValue())
                .subtract(modelPortfolioRepository.findByDate(LocalDate.now()).getEmergingMarketValue());

        BigDecimal commissionValue = inflationValuationService.findByDate(LocalDate.now()).getCommissionRate();
        BigDecimal price = inflationValuationService.findByDate(LocalDate.now()).getPrice();
        BigDecimal bondsQuotedModelValuation = remainingCapital.multiply(BONDS_INDEXED_PERCENTAGE);
        BigDecimal capital = bondsQuotedModelValuation.subtract(commissionValue);

        BigDecimal bondsNumber = capital.divide(price, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = bondsNumber.multiply(price);
        BigDecimal actualPercentage = entireStocksValuation.divide(investmentCapital, 2, RoundingMode.HALF_UP);

        myModelPortfolio.setBondsIndexedValue(entireStocksValuation);
        myModelPortfolio.setBondsIndexedPercentage(actualPercentage);
        inflationIndexedBondsInvestment.setQuantity(bondsNumber);
        inflationValuationService.findByDate(LocalDate.now()).setValuation(entireStocksValuation);

        inflationIndexedInvestmentRepository.save(inflationIndexedBondsInvestment);
        modelPortfolioRepository.save(myModelPortfolio);
    }
}
