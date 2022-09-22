package com.restapi.financialfortressbackend.mapper;

import com.restapi.financialfortressbackend.domain.investment.GoldInvestment;
import com.restapi.financialfortressbackend.domain.valuation.GoldValuation;
import com.restapi.financialfortressbackend.domain.investment.dto.GoldInvestmentDto;
import com.restapi.financialfortressbackend.domain.valuation.dto.GoldValuationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoldMapper {
    public GoldValuationDto mapToGoldValuationDto(GoldValuation goldValuation) {
        return new GoldValuationDto(
                goldValuation.getId(),
                goldValuation.getDate(),
                goldValuation.getValuation()
        );
    }

    public GoldInvestmentDto matToGoldInvestmentDto(GoldInvestment goldInvestment) {
        return new GoldInvestmentDto(
                goldInvestment.getId(),
                goldInvestment.getDate(),
                goldInvestment.getQuantity(),
                goldInvestment.getEntireValuation()
        );
    }


    public List<GoldValuationDto> matToGoldListDto(List<GoldValuation> list) {
        return list.stream()
                .map(this::mapToGoldValuationDto)
                .collect(Collectors.toList());
    }

    public List<GoldInvestmentDto> matToGoldInvestmentListDto(List<GoldInvestment> list) {
        return list.stream()
                .map(this::matToGoldInvestmentDto)
                .collect(Collectors.toList());
    }
}
