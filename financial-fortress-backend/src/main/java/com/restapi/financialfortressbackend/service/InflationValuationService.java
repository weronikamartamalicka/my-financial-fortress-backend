package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.InflationIndexedBondsValuation;
import com.restapi.financialfortressbackend.repository.InflationValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class InflationValuationService {

    @Autowired
    InflationValuationRepository inflationValuationRepository;

    public InflationIndexedBondsValuation findByDate(LocalDate date) {
        return inflationValuationRepository.findByDate(date);
    }

    public void saveBondsValuation(InflationIndexedBondsValuation inflationIndexedBondsValuation) {
        inflationValuationRepository.save(inflationIndexedBondsValuation);
    }

    public List<InflationIndexedBondsValuation> getAll() {
        return inflationValuationRepository.findAll();
    }

    public BigDecimal getEntireValuation(BigDecimal interestRate) {
        int size = inflationValuationRepository.findAll().size();
        if(size!=0) {
            BigDecimal actualEntireValue = inflationValuationRepository.findAll().get(size - 1).getEntireValuation();
            BigDecimal interests =  actualEntireValue.multiply(interestRate);

            return actualEntireValue.add(interests);
        } else {
            return BigDecimal.valueOf(0);
        }
    }
}
