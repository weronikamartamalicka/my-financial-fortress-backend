package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.EmergingMarketStocksValuation;
import com.restapi.financialfortressbackend.repository.EmergingMarketValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmergingMarketValuationService {

    @Autowired
    EmergingMarketValuationRepository emergingMarketValuationRepository;

    public EmergingMarketStocksValuation findTopByDate() {
        return emergingMarketValuationRepository.findAll().get(emergingMarketValuationRepository.findAll().size() - 1);
    }

    public BigDecimal getOneShareValue(BigDecimal dayStockValuation, BigDecimal exchange) {
        return dayStockValuation.multiply(exchange);
    }

    public void saveEmergingMarketValuation(EmergingMarketStocksValuation emergingMarketValuation) {
        emergingMarketValuationRepository.save(emergingMarketValuation);
    }

    public List<EmergingMarketStocksValuation> getAll() {
        return emergingMarketValuationRepository.findAll();
    }
}
