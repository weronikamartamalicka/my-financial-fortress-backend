package com.restapi.financialfortressbackend.mapper;

import com.restapi.financialfortressbackend.domain.DevelopedMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.DevelopedMarketStocksValuation;
import com.restapi.financialfortressbackend.domain.dto.DevelopedMarketStocksDto;
import com.restapi.financialfortressbackend.domain.dto.DevelopedMarketValuationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DevelopedMarketStocksMapper {
    public DevelopedMarketStocksDto mapToDevelopedInvestmentDto(DevelopedMarketStocksInvestment developedInvestment) {
        return new DevelopedMarketStocksDto(
                developedInvestment.getId(),
                developedInvestment.getQuantity()
        );
    }

    public DevelopedMarketValuationDto mapToDevelopedValuationDto(DevelopedMarketStocksValuation developedValuation) {
        return new DevelopedMarketValuationDto(
                developedValuation.getId(),
                developedValuation.getDate(),
                developedValuation.getValuation(),
                developedValuation.getCommissionRate(),
                developedValuation.getEntireValuation()
        );
    }

    public List<DevelopedMarketValuationDto> mapToDevelopedValuationListDto(List<DevelopedMarketStocksValuation> list) {
        return list.stream()
                .map(this::mapToDevelopedValuationDto)
                .collect(Collectors.toList());
    }
}
