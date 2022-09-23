package com.restapi.financialfortressbackend.service.investment;

import com.google.common.collect.Iterables;
import com.restapi.financialfortressbackend.domain.investment.DevelopedMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.investment.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.domain.investment.SimpleInvestment;
import com.restapi.financialfortressbackend.repository.investment.DevelopedMarketInvestmentRepository;
import com.restapi.financialfortressbackend.service.valuation.DevelopedMarketValuationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DevelopedMarketStocksService {

    private static final BigDecimal DEVELOPED_MARKET_PERCENTAGE = new BigDecimal(0.15);
    private final DevelopedMarketValuationService developedMarketValuationService;
    private final DevelopedMarketInvestmentRepository developedMarketRepository;

    public void calculateInstrumentComposition(BigDecimal investmentCapital, ModelPortfolioInvestment modelPortfolio) {

        DevelopedMarketStocksInvestment developedMarketStocksInvestment = new DevelopedMarketStocksInvestment();
        ZoneId z = ZoneId.of( "Europe/Warsaw" );
        developedMarketStocksInvestment.setDate(LocalDateTime.now(z));

        BigDecimal saleValuation = developedMarketValuationService.findTopByDate().getValuation();
        BigDecimal commissionValue = developedMarketValuationService.findTopByDate().getCommissionRate();
        BigDecimal developedMarketModelValuation = investmentCapital.multiply(DEVELOPED_MARKET_PERCENTAGE);
        BigDecimal capital = developedMarketModelValuation.subtract(commissionValue);

        BigDecimal stocksNumber = capital.divide(saleValuation, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = stocksNumber.multiply(saleValuation);

        modelPortfolio.setDevelopedMarketValue(entireStocksValuation);
        developedMarketStocksInvestment.setQuantity(stocksNumber);
        developedMarketStocksInvestment.setEntireValuation(entireStocksValuation);

        developedMarketRepository.save(developedMarketStocksInvestment);
    }

    public DevelopedMarketStocksInvestment findByType(String type) {
        return Iterables.getLast(developedMarketRepository.findByType(type)
                .orElse(List.of(new DevelopedMarketStocksInvestment(BigDecimal.valueOf(0)))));
    }

    public void deleteAll() {
        developedMarketRepository.deleteAll();
    }

    public List<DevelopedMarketStocksInvestment> findAll() {
        return developedMarketRepository.findAll();
    }

    public DevelopedMarketStocksInvestment findTopByDate() {
        return developedMarketRepository.findAll().get(developedMarketRepository.findAll().size() - 1);
    }
    public void saveInvestment(DevelopedMarketStocksInvestment developedMarketStocksInvestment) {
        developedMarketRepository.save(developedMarketStocksInvestment);
    }
}
