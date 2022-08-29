package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.EmergingMarketStocksInvestment;
import com.restapi.financialfortressbackend.exception.EmergingMarketStocksNotFoundException;
import com.restapi.financialfortressbackend.repository.EmergingMarketStocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmergingMarketStocksService {

    @Autowired
    EmergingMarketStocksRepository emergingMarketRepository;

    EmergingMarketStocksInvestment findByDate(LocalDate date) throws EmergingMarketStocksNotFoundException {
        return emergingMarketRepository.findByDate(date).orElseThrow(EmergingMarketStocksNotFoundException::new);
    }
}
