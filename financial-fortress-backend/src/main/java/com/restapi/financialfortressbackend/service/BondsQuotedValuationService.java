package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketValuation;
import com.restapi.financialfortressbackend.repository.BondsQuotedValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BondsQuotedValuationService {

    @Autowired
    BondsQuotedValuationRepository bondsQuotedValuationRepository;

    BondsQuotedOnTheMarketValuation findByDate(LocalDate date) {
        return bondsQuotedValuationRepository.findByDate(date);
    }
}
