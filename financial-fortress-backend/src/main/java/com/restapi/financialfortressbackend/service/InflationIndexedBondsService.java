package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.InflationIndexedBondsInvestment;
import com.restapi.financialfortressbackend.exception.InflationIndexedNotFoundException;
import com.restapi.financialfortressbackend.repository.InflationIndexedBondsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InflationIndexedBondsService {

    @Autowired
    InflationIndexedBondsRepository inflationIndexedBondsRepository;

    InflationIndexedBondsInvestment findByDate(LocalDate date) throws InflationIndexedNotFoundException {
        return inflationIndexedBondsRepository.findByDate(date).orElseThrow(InflationIndexedNotFoundException::new);
    }
}
