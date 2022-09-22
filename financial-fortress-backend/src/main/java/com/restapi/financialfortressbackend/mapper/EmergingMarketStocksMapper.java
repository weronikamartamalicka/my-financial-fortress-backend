package com.restapi.financialfortressbackend.mapper;

import com.restapi.financialfortressbackend.domain.investment.EmergingMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.valuation.EmergingMarketStocksValuation;
import com.restapi.financialfortressbackend.domain.investment.dto.EmergingMarketStocksDto;
import com.restapi.financialfortressbackend.domain.valuation.dto.EmergingMarketValuationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmergingMarketStocksMapper {

    public EmergingMarketStocksDto mapToEmergingInvestmentDto(EmergingMarketStocksInvestment emergingInvestment) {
        return new EmergingMarketStocksDto(
                emergingInvestment.getId(),
                emergingInvestment.getDate(),
                emergingInvestment.getQuantity(),
                emergingInvestment.getEntireValuation()
        );
    }

    public EmergingMarketValuationDto mapToEmergingValuationDto(EmergingMarketStocksValuation emergingValuation) {
        return new EmergingMarketValuationDto(
                emergingValuation.getId(),
                emergingValuation.getDate(),
                emergingValuation.getValuation(),
                emergingValuation.getCommissionRate()
        );
    }

    public List<EmergingMarketValuationDto> mapToEmergingValuationListDto(List<EmergingMarketStocksValuation> list) {
        return list.stream()
                .map(this::mapToEmergingValuationDto)
                .collect(Collectors.toList());
    }

    public List<EmergingMarketStocksDto> mapToEmergingInvestmentListDto(List<EmergingMarketStocksInvestment> list) {
        return list.stream()
                .map(this::mapToEmergingInvestmentDto)
                .collect(Collectors.toList());
    }
}
