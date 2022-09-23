package com.restapi.financialfortressbackend.service.valuation;

import com.restapi.financialfortressbackend.domain.valuation.BondsQuotedOnTheMarketValuation;
import com.restapi.financialfortressbackend.domain.valuation.SimpleValuation;
import com.restapi.financialfortressbackend.repository.valuation.BondsQuotedValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
