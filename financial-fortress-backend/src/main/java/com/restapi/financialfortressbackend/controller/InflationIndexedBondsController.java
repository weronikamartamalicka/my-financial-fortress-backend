package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.InflationClient;
import com.restapi.financialfortressbackend.domain.InflationIndexedBondsValuation;
import com.restapi.financialfortressbackend.domain.dto.InflationIndexedBondsDto;
import com.restapi.financialfortressbackend.domain.dto.InflationValuationDto;
import com.restapi.financialfortressbackend.mapper.InflationMapper;
import com.restapi.financialfortressbackend.service.InflationIndexedBondsService;
import com.restapi.financialfortressbackend.service.InflationValuationService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Scheduled(cron = "0 0 10 15 * *")
    @RequestMapping(method = RequestMethod.POST, value = "/inflation/value")
    public void saveNewValuation() {

        InflationIndexedBondsValuation inflationIndexedBondsValuation = new InflationIndexedBondsValuation();
        inflationIndexedBondsValuation.setDate(LocalDate.now());
        BigDecimal inflationRate = inflationClient.getInflationRate();
        inflationIndexedBondsValuation.setValuation(inflationRate);

        Optional<BigDecimal> interestRate = Optional.ofNullable(inflationIndexedBondsService
                .findByType(inflationIndexedBondsValuation.getType()).getInterestRate());

        inflationIndexedBondsValuation.setInterestsValuation(interestRate.orElse(BigDecimal.ZERO).add(inflationRate));

        BigDecimal entireValuation = inflationValuationService.getEntireValuation(
                interestRate.orElse(BigDecimal.ZERO).add(inflationRate));

        inflationIndexedBondsValuation.setEntireValuation(entireValuation);

        inflationValuationService.saveBondsValuation(inflationIndexedBondsValuation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/inflation/invest/{type}")
    public InflationIndexedBondsDto getInvestmentInfo(@PathVariable String type) {
        return inflationMapper.mapToInflationBondsInvestmentDto(inflationIndexedBondsService.findByType(type));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/inflation/value")
    public List<InflationValuationDto> getAllValuations() {
        return inflationMapper.mapToInflationBondsListDto(inflationValuationService.getAll());
    }
}
