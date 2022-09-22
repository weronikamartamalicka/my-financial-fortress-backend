package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.valuation.GoldValuation;
import com.restapi.financialfortressbackend.domain.response.Root;
import com.restapi.financialfortressbackend.repository.GoldValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class GoldValuationService {

    @Autowired
    GoldValuationRepository goldValuationRepository;

    public BigDecimal getOneCoinValue(Root root) {

        BigDecimal apiRate = BigDecimal.valueOf(root.getRates().getXAU());
        BigDecimal troyOzPrice = BigDecimal.ONE.divide(apiRate, 2, RoundingMode.HALF_UP);
        BigDecimal ozPrice = troyOzPrice.divide(BigDecimal.valueOf(31), 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(28));
        BigDecimal halfOzCoinPrice = ozPrice.divide(BigDecimal.valueOf(2), 0, RoundingMode.HALF_UP);

        return halfOzCoinPrice;
    }

    public void saveGoldValuation(GoldValuation goldValuation) {
        goldValuationRepository.save(goldValuation);
    }

    public List<GoldValuation> getAll() {
        return goldValuationRepository.findAll();
    }

    public GoldValuation findTopByDate() {

        return goldValuationRepository.findAll().get(goldValuationRepository.findAll().size() - 1);
    }
}
