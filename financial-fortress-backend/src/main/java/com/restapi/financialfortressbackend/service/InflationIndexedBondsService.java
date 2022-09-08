package com.restapi.financialfortressbackend.service;

import com.google.common.collect.Iterables;
import com.restapi.financialfortressbackend.domain.InflationIndexedBondsInvestment;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.repository.InflationIndexedInvestmentRepository;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

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
                .subtract(modelPortfolio.getBondsQuotedValue())
                .subtract(modelPortfolio.getDevelopedMarketValue())
                .subtract(modelPortfolio.getGoldValue())
                .subtract(modelPortfolio.getEmergingMarketValue());

        BigDecimal price = inflationIndexedBondsInvestment.getPrice();
        BigDecimal capital = remainingCapital;

        BigDecimal bondsNumber = capital.divide(price, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = bondsNumber.multiply(price);

        modelPortfolio.setBondsIndexedValue(entireStocksValuation);
        inflationIndexedBondsInvestment.setQuantity(bondsNumber);
        inflationIndexedBondsInvestment.setEntireValuation(entireStocksValuation);

        inflationIndexedInvestmentRepository.save(inflationIndexedBondsInvestment);
    }

    public InflationIndexedBondsInvestment findByType(String type) {
        return Iterables.getLast(inflationIndexedInvestmentRepository.findByType(type)
                .orElse(List.of(new InflationIndexedBondsInvestment(BigDecimal.valueOf(0)))));
    }

    public void deleteAll() {
        inflationIndexedInvestmentRepository.deleteAll();
    }

    public List<InflationIndexedBondsInvestment> findAll() {
        return inflationIndexedInvestmentRepository.findAll();
    }

    public InflationIndexedBondsInvestment findTopByDate() {
        return inflationIndexedInvestmentRepository.findAll().get(inflationIndexedInvestmentRepository.findAll().size() - 1);
    }

    public void saveInflationIndexedInvestment(InflationIndexedBondsInvestment inflationIndexedInvestment) {
        inflationIndexedInvestmentRepository.save(inflationIndexedInvestment);
    }
}
