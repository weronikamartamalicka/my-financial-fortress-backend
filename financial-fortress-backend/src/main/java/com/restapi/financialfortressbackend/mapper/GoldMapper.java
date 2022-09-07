package com.restapi.financialfortressbackend.mapper;

import com.restapi.financialfortressbackend.domain.GoldInvestment;
import com.restapi.financialfortressbackend.domain.GoldValuation;
import com.restapi.financialfortressbackend.domain.dto.GoldInvestmentDto;
import com.restapi.financialfortressbackend.domain.dto.GoldValuationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoldMapper {
    public GoldValuationDto mapToGoldValuationDto(GoldValuation goldValuation) {

        return new GoldValuationDto(
                goldValuation.getId(), goldValuation.getDate(),
                 goldValuation.getOneCoinPrice(),
                goldValuation.getEntireValuation());
    }

    public GoldInvestmentDto matToGoldInvestmentDto(GoldInvestment goldInvestment) {

        return new GoldInvestmentDto(goldInvestment.getId(), goldInvestment.getQuantity());
    }


    public List<GoldValuationDto> matToGoldListDto(List<GoldValuation> list) {
        return list.stream()
                .map(this::mapToGoldValuationDto)
                .collect(Collectors.toList());
    }
}
