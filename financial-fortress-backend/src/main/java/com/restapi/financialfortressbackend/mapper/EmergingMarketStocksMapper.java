package com.restapi.financialfortressbackend.mapper;

import com.restapi.financialfortressbackend.domain.EmergingMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.EmergingMarketStocksValuation;
import com.restapi.financialfortressbackend.domain.dto.EmergingMarketStocksDto;
import com.restapi.financialfortressbackend.domain.dto.EmergingMarketValuationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmergingMarketStocksMapper {

    public EmergingMarketStocksDto mapToEmergingInvestmentDto(EmergingMarketStocksInvestment emergingInvestment) {
        return new EmergingMarketStocksDto(
                emergingInvestment.getId(),
                emergingInvestment.getQuantity()
        );
    }

    public EmergingMarketValuationDto mapToEmergingValuationDto(EmergingMarketStocksValuation emergingValuation) {
        return new EmergingMarketValuationDto(
                emergingValuation.getId(),
                emergingValuation.getDate(),
                emergingValuation.getValuation(),
                emergingValuation.getCommissionRate(),
                emergingValuation.getEntireValuation()
        );
    }

    public List<EmergingMarketValuationDto> mapToEmergingValuationListDto(List<EmergingMarketStocksValuation> list) {
        return list.stream()
                .map(this::mapToEmergingValuationDto)
                .collect(Collectors.toList());
    }
}
