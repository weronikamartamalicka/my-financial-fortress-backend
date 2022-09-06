package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.EmergingMarketStocksValuation;
import com.restapi.financialfortressbackend.repository.EmergingMarketValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmergingMarketValuationService {

    @Autowired
    EmergingMarketValuationRepository emergingMarketValuationRepository;

    public EmergingMarketStocksValuation findByDate(LocalDate date) {
        return emergingMarketValuationRepository.findByDate(date);
    }

    public BigDecimal getOneShareValue(BigDecimal dayStockValuation, BigDecimal exchange) {
        return dayStockValuation.multiply(exchange);
    }

    public void saveDevelopedMarketValuation(EmergingMarketStocksValuation emergingMarketValuation) {
        emergingMarketValuationRepository.save(emergingMarketValuation);
    }

    public List<EmergingMarketStocksValuation> getAll() {
        return emergingMarketValuationRepository.findAll();
    }
}
