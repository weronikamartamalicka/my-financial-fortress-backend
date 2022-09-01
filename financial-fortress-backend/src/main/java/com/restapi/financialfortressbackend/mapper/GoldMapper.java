package com.restapi.financialfortressbackend.mapper;

import com.restapi.financialfortressbackend.domain.GoldValuation;
import com.restapi.financialfortressbackend.domain.dto.GoldValuationDto;
import org.springframework.stereotype.Service;

@Service
public class GoldMapper {
    public GoldValuationDto mapToGoldValuationDto() {
        return new GoldValuationDto();
    }

    public GoldValuation mapToGoldValuation() {
        return new GoldValuation();
    }
}
