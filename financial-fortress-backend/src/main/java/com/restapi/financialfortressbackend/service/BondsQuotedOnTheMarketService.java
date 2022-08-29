package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketInvestment;
import com.restapi.financialfortressbackend.exception.BondsQuotedNotFoundException;
import com.restapi.financialfortressbackend.repository.BondsQuotedOnTheMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BondsQuotedOnTheMarketService {

    @Autowired
    BondsQuotedOnTheMarketRepository bondsQuotedOnTheMarketRepository;

    BondsQuotedOnTheMarketInvestment findByDate(LocalDate date) throws BondsQuotedNotFoundException {
        return bondsQuotedOnTheMarketRepository.findByDate(date).orElseThrow(BondsQuotedNotFoundException::new);
    }
}
