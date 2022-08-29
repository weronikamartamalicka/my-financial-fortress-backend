package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.DevelopedMarketStocksInvestment;
import com.restapi.financialfortressbackend.exception.DevelopedMarketStocksNotFoundException;
import com.restapi.financialfortressbackend.repository.DevelopedMarketStocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DevelopedMarketStocksService {

    @Autowired
    DevelopedMarketStocksRepository developedMarketRepository;

    DevelopedMarketStocksInvestment findByDate(LocalDate date) throws DevelopedMarketStocksNotFoundException {
        return developedMarketRepository.findByDate(date).orElseThrow(DevelopedMarketStocksNotFoundException::new);
    }
}
