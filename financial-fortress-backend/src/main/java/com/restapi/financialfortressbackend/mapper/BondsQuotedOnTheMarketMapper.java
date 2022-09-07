package com.restapi.financialfortressbackend.mapper;

import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketInvestment;
import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketValuation;
import com.restapi.financialfortressbackend.domain.dto.BondsQuotedOnTheMarketDto;
import com.restapi.financialfortressbackend.domain.dto.BondsQuotedValuationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class BondsQuotedOnTheMarketMapper {
    public BondsQuotedOnTheMarketDto mapToBondsQuotedInvestmentDto(BondsQuotedOnTheMarketInvestment bonds) {

        return new BondsQuotedOnTheMarketDto(
                bonds.getId(),
                bonds.getQuantity(),
                bonds.getCouponRate(),
                bonds.getRedemptionDate(),
                bonds.getInterestPeriod()
        );
    }

    public BondsQuotedValuationDto mapToBondsValuationDto(BondsQuotedOnTheMarketValuation bonds) {

        return new BondsQuotedValuationDto(
                bonds.getId(),
                bonds.getDate(),
                bonds.getValuation(),
                bonds.getCommissionRate(),
                bonds.getEntireValuation()
        );
    }

    public List<BondsQuotedValuationDto> mapToBondsQuotedListDto(List<BondsQuotedOnTheMarketValuation> list) {
        return list.stream()
                .map(this::mapToBondsValuationDto)
                .collect(Collectors.toList());
    }

    public List<BondsQuotedOnTheMarketDto> mapToBondsQuotedInvestmentListDto(List<BondsQuotedOnTheMarketInvestment> list) {
        return list.stream()
                .map(this::mapToBondsQuotedInvestmentDto)
                .collect(Collectors.toList());
    }
}
