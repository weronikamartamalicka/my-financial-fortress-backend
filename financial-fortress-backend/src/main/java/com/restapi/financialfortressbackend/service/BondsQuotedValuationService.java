package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketValuation;
import com.restapi.financialfortressbackend.repository.BondsQuotedValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BondsQuotedValuationService {
    @Autowired
    BondsQuotedValuationRepository bondsQuotedValuationRepository;

    BondsQuotedOnTheMarketValuation findByDate(LocalDate date) {
        return bondsQuotedValuationRepository.findByDate(date);
    }

    public List<BondsQuotedOnTheMarketValuation> getAll() {
        return bondsQuotedValuationRepository.findAll();
    }

    public void saveBondsQuotedValuation(BondsQuotedOnTheMarketValuation bondsQuotedValuation) {
        bondsQuotedValuationRepository.save(bondsQuotedValuation);
    }
}
