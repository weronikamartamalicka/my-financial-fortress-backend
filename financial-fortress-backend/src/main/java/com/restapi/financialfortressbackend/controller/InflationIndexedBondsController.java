package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.InflationClient;
import com.restapi.financialfortressbackend.domain.InflationIndexedBondsInvestment;
import com.restapi.financialfortressbackend.domain.InflationIndexedBondsValuation;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.domain.dto.InflationIndexedBondsDto;
import com.restapi.financialfortressbackend.domain.dto.InflationValuationDto;
import com.restapi.financialfortressbackend.mapper.InflationMapper;
import com.restapi.financialfortressbackend.service.InflationIndexedBondsService;
import com.restapi.financialfortressbackend.service.InflationValuationService;
import com.restapi.financialfortressbackend.service.ModelPortfolioService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController()
@CrossOrigin(value = "*")
@AllArgsConstructor
@RequestMapping(value = "/v1")
public class InflationIndexedBondsController {
    private final InflationClient inflationClient;
    private final InflationValuationService inflationValuationService;
    private final InflationIndexedBondsService inflationIndexedBondsService;
    private final InflationMapper inflationMapper;
    private final ModelPortfolioService modelPortfolioService;

    @Scheduled(cron = "0 0 10 15 * *")
    @RequestMapping(method = RequestMethod.POST, value = "/inflation/value")
    public void saveNewValuation() {

        InflationIndexedBondsValuation inflationIndexedBondsValuation = new InflationIndexedBondsValuation();
        inflationIndexedBondsValuation.setDate(LocalDateTime.now());
        BigDecimal inflationRate = inflationClient.getInflationRate();
        inflationIndexedBondsValuation.setValuation(inflationRate);

        BigDecimal interestRate = inflationIndexedBondsService.findByType(inflationIndexedBondsValuation
                .getType())
                .orElse(new InflationIndexedBondsInvestment(BigDecimal.valueOf(0)))
                .getInterestRate();

        inflationIndexedBondsValuation.setInterestsValuation(interestRate.add(inflationRate));

        BigDecimal entireValuation = inflationValuationService.getEntireValuation(
                interestRate.add(inflationRate));

        inflationIndexedBondsValuation.setEntireValuation(entireValuation);

        ModelPortfolioInvestment modelPortfolioInvestment = new ModelPortfolioInvestment();
        modelPortfolioInvestment.setDate(LocalDateTime.now());
        modelPortfolioInvestment.setBondsIndexedValue(entireValuation);

        modelPortfolioService.calculatePercentageComposition();

        inflationValuationService.saveBondsValuation(inflationIndexedBondsValuation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/inflation/invest")
    public InflationIndexedBondsDto getInvestmentInfo() {
        return inflationMapper.mapToInflationBondsInvestmentDto(inflationIndexedBondsService.findByType(type));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/inflation/value")
    public List<InflationValuationDto> getAllValuations() {
        return inflationMapper.mapToInflationBondsListDto(inflationValuationService.getAll());
    }
}
