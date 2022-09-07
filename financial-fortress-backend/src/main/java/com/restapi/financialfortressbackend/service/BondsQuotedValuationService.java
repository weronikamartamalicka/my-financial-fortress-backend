package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketValuation;
import com.restapi.financialfortressbackend.repository.BondsQuotedValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BondsQuotedValuationService {
    @Autowired
    BondsQuotedValuationRepository bondsQuotedValuationRepository;

    public BondsQuotedOnTheMarketValuation findTopByDate() {
        return bondsQuotedValuationRepository.findAll().get(bondsQuotedValuationRepository.findAll().size() - 1);
    }

    public List<BondsQuotedOnTheMarketValuation> getAll() {
        return bondsQuotedValuationRepository.findAll();
    }

    public void saveBondsQuotedValuation(BondsQuotedOnTheMarketValuation bondsQuotedValuation) {
        bondsQuotedValuationRepository.save(bondsQuotedValuation);
    }
}
