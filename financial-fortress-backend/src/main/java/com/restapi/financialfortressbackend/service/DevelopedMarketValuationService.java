package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.valuation.DevelopedMarketStocksValuation;
import com.restapi.financialfortressbackend.repository.DevelopedMarketValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DevelopedMarketValuationService {

    @Autowired
    DevelopedMarketValuationRepository developedMarketValuationRepository;

    public DevelopedMarketStocksValuation findTopByDate() {
        return developedMarketValuationRepository.findAll().get(developedMarketValuationRepository.findAll().size() - 1);
    }

    public void saveDevelopedMarketValuation(DevelopedMarketStocksValuation developedMarketValuation) {
        developedMarketValuationRepository.save(developedMarketValuation);
    }

    public BigDecimal getOneShareValue(BigDecimal dayStockValuation, BigDecimal exchange) {
        return dayStockValuation.multiply(exchange);
    }

    public List<DevelopedMarketStocksValuation> getAll() {
        return developedMarketValuationRepository.findAll();
    }
}
