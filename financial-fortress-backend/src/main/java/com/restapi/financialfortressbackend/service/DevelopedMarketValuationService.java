package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.DevelopedMarketStocksValuation;
import com.restapi.financialfortressbackend.repository.DevelopedMarketValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DevelopedMarketValuationService {

    @Autowired
    DevelopedMarketValuationRepository developedMarketValuationRepository;

    public DevelopedMarketStocksValuation findByDate(LocalDate date) {
        return developedMarketValuationRepository.findByDate(date);
    }
}
