package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.InflationIndexedBondsValuation;
import com.restapi.financialfortressbackend.repository.InflationValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InflationValuationService {

    @Autowired
    InflationValuationRepository inflationValuationRepository;

    InflationIndexedBondsValuation findByDate(LocalDate date) {
        return inflationValuationRepository.findByDate(date);
    }
}
