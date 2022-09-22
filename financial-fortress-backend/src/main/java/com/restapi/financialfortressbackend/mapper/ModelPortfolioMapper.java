package com.restapi.financialfortressbackend.mapper;

import com.restapi.financialfortressbackend.domain.investment.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.domain.investment.dto.ModelPortfolioDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelPortfolioMapper {

    public ModelPortfolioInvestment mapToModelPortfolio(ModelPortfolioDto modelPortfolioDto) {
        return new ModelPortfolioInvestment(
                modelPortfolioDto.getId(),
                modelPortfolioDto.getDate(),
                modelPortfolioDto.getEntireValue(),
                modelPortfolioDto.getGoldValue(),
                modelPortfolioDto.getBondsQuotedValue(),
                modelPortfolioDto.getBondsIndexedValue(),
                modelPortfolioDto.getDevelopedMarketValue(),
                modelPortfolioDto.getEmergingMarketValue(),
                modelPortfolioDto.getGoldPercentage(),
                modelPortfolioDto.getBondsQuotedPercentage(),
                modelPortfolioDto.getBondsIndexedPercentage(),
                modelPortfolioDto.getDevelopedMarketPercentage(),
                modelPortfolioDto.getEmergingMarketPercentage()
        );
    }

    public ModelPortfolioDto mapToModelPortfolioDto(ModelPortfolioInvestment modelPortfolioInvestment) {
        return new ModelPortfolioDto(
                modelPortfolioInvestment.getId(),
                modelPortfolioInvestment.getDate(),
                modelPortfolioInvestment.getEntireValue(),
                modelPortfolioInvestment.getGoldValue(),
                modelPortfolioInvestment.getBondsQuotedValue(),
                modelPortfolioInvestment.getBondsIndexedValue(),
                modelPortfolioInvestment.getDevelopedMarketValue(),
                modelPortfolioInvestment.getEmergingMarketValue(),
                modelPortfolioInvestment.getGoldPercentage(),
                modelPortfolioInvestment.getBondsQuotedPercentage(),
                modelPortfolioInvestment.getBondsIndexedPercentage(),
                modelPortfolioInvestment.getDevelopedMarketPercentage(),
                modelPortfolioInvestment.getEmergingMarketPercentage()
        );
    }

    public List<ModelPortfolioDto> mapToModelPortfolioDtoList(List<ModelPortfolioInvestment> list) {
        return list.stream()
                .map(this::mapToModelPortfolioDto)
                .collect(Collectors.toList());
    }
}
