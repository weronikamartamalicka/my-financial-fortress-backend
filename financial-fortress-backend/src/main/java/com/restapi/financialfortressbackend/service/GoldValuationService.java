package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.GoldValuation;
import com.restapi.financialfortressbackend.domain.dto.Root;
import com.restapi.financialfortressbackend.repository.GoldValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class GoldValuationService {

    @Autowired
    GoldValuationRepository goldValuationRepository;

    public GoldValuation findByDate(LocalDate date) {
        return goldValuationRepository.findByDate(date);
    }

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
}
