package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.GoldInvestment;
import com.restapi.financialfortressbackend.exception.GoldNotFoundException;
import com.restapi.financialfortressbackend.repository.GoldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GoldService {

    @Autowired
    GoldRepository goldRepository;

    GoldInvestment findByDate(LocalDate date) throws GoldNotFoundException {
        return goldRepository.findByDate(date).orElseThrow(GoldNotFoundException::new);
    }
}
