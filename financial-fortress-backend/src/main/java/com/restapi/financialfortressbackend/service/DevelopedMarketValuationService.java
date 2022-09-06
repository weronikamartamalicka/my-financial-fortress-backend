package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.DevelopedMarketStocksValuation;
import com.restapi.financialfortressbackend.repository.DevelopedMarketValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DevelopedMarketValuationService {

    @Autowired
    DevelopedMarketValuationRepository developedMarketValuationRepository;

    public DevelopedMarketStocksValuation findByDate(LocalDate date) {
        return developedMarketValuationRepository.findByDate(date);
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
