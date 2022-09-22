package com.restapi.financialfortressbackend.mapper;

import com.restapi.financialfortressbackend.domain.investment.InflationIndexedBondsInvestment;
import com.restapi.financialfortressbackend.domain.valuation.InflationIndexedBondsValuation;
import com.restapi.financialfortressbackend.domain.investment.dto.InflationIndexedBondsDto;
import com.restapi.financialfortressbackend.domain.valuation.dto.InflationValuationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InflationMapper {
    public InflationIndexedBondsDto mapToInflationBondsInvestmentDto(InflationIndexedBondsInvestment inflationBonds) {
        return new InflationIndexedBondsDto(
                inflationBonds.getId(),
                inflationBonds.getDate(),
                inflationBonds.getQuantity(),
                inflationBonds.getRedemptionDate(),
                inflationBonds.getEntireValuation()
        );
    }


    public List<InflationValuationDto> mapToInflationBondsListDto(List<InflationIndexedBondsValuation> list) {
        return list.stream()
                .map(this::mapToInflationValuationDto)
                .collect(Collectors.toList());
    }

    private InflationValuationDto mapToInflationValuationDto(InflationIndexedBondsValuation inflationValuation) {
        return new InflationValuationDto(
                inflationValuation.getId(),
                inflationValuation.getDate(),
                inflationValuation.getValuation(),
                inflationValuation.getInterestsValuation()
        );
    }

    public List<InflationIndexedBondsDto> mapToInflationBondsInvestmentListDto(List<InflationIndexedBondsInvestment> list) {
        return list.stream()
                .map(this::mapToInflationBondsInvestmentDto)
                .collect(Collectors.toList());
    }
}
