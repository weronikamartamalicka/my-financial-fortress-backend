package com.restapi.financialfortressbackend.service.investment;

import com.google.common.collect.Iterables;
import com.restapi.financialfortressbackend.domain.investment.GoldInvestment;
import com.restapi.financialfortressbackend.domain.investment.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.repository.investment.GoldInvestmentRepository;
import com.restapi.financialfortressbackend.service.valuation.GoldValuationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoldInvestmentService {

    private static final BigDecimal GOLD_PERCENTAGE = new BigDecimal(0.1);
    private final GoldInvestmentRepository goldRepository;
    private final GoldValuationService goldValuationService;

    public void calculateInstrumentComposition(BigDecimal investmentCapital, ModelPortfolioInvestment modelPortfolio) {

        GoldInvestment goldInvestment = new GoldInvestment();
        ZoneId z = ZoneId.of( "Europe/Warsaw" );
        goldInvestment.setDate(LocalDateTime.now(z));

        Optional<BigDecimal> goldSale = Optional.ofNullable(
                goldValuationService.findTopByDate().getValuation());

        goldSale.orElse(new BigDecimal(4562));

        BigDecimal goldPurchase = goldInvestment.getPurchaseValuation();
        BigDecimal goldModelValuation = investmentCapital.multiply(GOLD_PERCENTAGE);

        if (goldModelValuation.compareTo(goldPurchase) == -1) {
            modelPortfolio.setGoldValue(goldPurchase);
            goldInvestment.setQuantity(new BigDecimal(1));
            goldInvestment.setEntireValuation(goldSale.get());

        } else {
            BigDecimal coinsQuantity = goldModelValuation.divide(goldPurchase, 0, RoundingMode.DOWN);
            goldInvestment.setQuantity(coinsQuantity);
            BigDecimal actualValue = coinsQuantity.multiply(goldSale.get());
            goldInvestment.setEntireValuation(actualValue);
            modelPortfolio.setGoldValue(actualValue);
        }

        goldRepository.save(goldInvestment);
    }

    public GoldInvestment findByType(String type) {
        return Iterables.getLast(goldRepository.findByType(type)
                .orElse(List.of(new GoldInvestment(BigDecimal.valueOf(0)))));
    }

    public void deleteAll() {
        goldRepository.deleteAll();
    }

    public List<GoldInvestment> findAll() {
        return goldRepository.findAll();
    }

    public GoldInvestment findTopByDate() {
        return  goldRepository.findAll().get(goldRepository.findAll().size() - 1);
    }

    public void saveInvestment(GoldInvestment goldInvestment) {
        goldRepository.save(goldInvestment);
    }
}
