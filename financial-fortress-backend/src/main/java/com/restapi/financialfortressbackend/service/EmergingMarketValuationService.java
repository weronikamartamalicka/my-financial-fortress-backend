package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.EmergingMarketStocksValuation;
import com.restapi.financialfortressbackend.repository.EmergingMarketValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmergingMarketValuationService {

    @Autowired
    EmergingMarketValuationRepository emergingMarketValuationRepository;

    public EmergingMarketStocksValuation findByDate(LocalDate date) {
        return emergingMarketValuationRepository.findByDate(date);
    }
}
