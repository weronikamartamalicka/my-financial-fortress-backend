package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.GoldValuation;
import com.restapi.financialfortressbackend.repository.GoldValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GoldValuationService {

    @Autowired
    GoldValuationRepository goldValuationRepository;

    public GoldValuation findByDate(LocalDate date) {
        return goldValuationRepository.findByDate(date);
    }
}
