package com.restapi.financialfortressbackend.mapper;

import com.restapi.financialfortressbackend.domain.InflationIndexedBondsInvestment;
import com.restapi.financialfortressbackend.domain.InflationIndexedBondsValuation;
import com.restapi.financialfortressbackend.domain.dto.InflationIndexedBondsDto;
import com.restapi.financialfortressbackend.domain.dto.InflationValuationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InflationMapper {
    public InflationIndexedBondsDto mapToInflationBondsInvestmentDto(InflationIndexedBondsInvestment inflationBonds) {
        return new InflationIndexedBondsDto(
                inflationBonds.getId(),
                inflationBonds.getQuantity(),
                inflationBonds.getRedemptionDate()
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
                inflationValuation.getInterestsValuation(),
                inflationValuation.getEntireValuation()
        );
    }
}
