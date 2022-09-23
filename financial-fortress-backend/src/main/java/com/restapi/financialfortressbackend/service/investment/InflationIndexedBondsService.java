package com.restapi.financialfortressbackend.service.investment;

import com.google.common.collect.Iterables;
import com.restapi.financialfortressbackend.domain.investment.InflationIndexedBondsInvestment;
import com.restapi.financialfortressbackend.domain.investment.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.domain.investment.SimpleInvestment;
import com.restapi.financialfortressbackend.repository.investment.InflationIndexedInvestmentRepository;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import com.restapi.financialfortressbackend.service.valuation.InflationValuationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InflationIndexedBondsService {

    private static final BigDecimal BONDS_INDEXED_PERCENTAGE = new BigDecimal(0.4);
    private final InflationIndexedInvestmentRepository inflationIndexedInvestmentRepository;

    public void calculateInstrumentComposition(BigDecimal investmentCapital, ModelPortfolioInvestment modelPortfolio) {

        InflationIndexedBondsInvestment inflationIndexedBondsInvestment = new InflationIndexedBondsInvestment();
        ZoneId z = ZoneId.of( "Europe/Warsaw" );
        inflationIndexedBondsInvestment.setDate(LocalDateTime.now(z));
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

    public void saveInvestment(InflationIndexedBondsInvestment inflationIndexedBondsInvestment) {
        inflationIndexedInvestmentRepository.save(inflationIndexedBondsInvestment);
    }
}
