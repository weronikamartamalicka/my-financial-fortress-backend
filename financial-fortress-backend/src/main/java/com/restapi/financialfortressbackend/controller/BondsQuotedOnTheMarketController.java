package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.BondsQuotedOnTheMarketClient;
import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketValuation;
import com.restapi.financialfortressbackend.domain.GoldValuation;
import com.restapi.financialfortressbackend.domain.dto.BondsQuotedOnTheMarketDto;
import com.restapi.financialfortressbackend.domain.dto.BondsQuotedValuationDto;
import com.restapi.financialfortressbackend.domain.dto.GoldInvestmentDto;
import com.restapi.financialfortressbackend.domain.dto.GoldValuationDto;
import com.restapi.financialfortressbackend.mapper.BondsQuotedOnTheMarketMapper;
import com.restapi.financialfortressbackend.service.BondsQuotedOnTheMarketService;
import com.restapi.financialfortressbackend.service.BondsQuotedValuationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@CrossOrigin(value = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class BondsQuotedOnTheMarketController {

    private final BondsQuotedOnTheMarketMapper bondsQuotedMapper;
    private final BondsQuotedOnTheMarketClient bondsQuotedClient;
    private final BondsQuotedOnTheMarketService bondsQuotedService;
    private final BondsQuotedValuationService bondsQuotedValuationService;

    @Scheduled(cron = "0 0 10 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "/bonds/quoted/value")
    public void saveNewValuation() {

        BondsQuotedOnTheMarketValuation bondsQuotedValuation = new BondsQuotedOnTheMarketValuation();

        bondsQuotedValuation.setDate(LocalDate.now());
        BigDecimal oneBondPrice = bondsQuotedClient.getBondsQuotedOnTheMarketValuation();
        bondsQuotedValuation.setValuation(bondsQuotedClient.getBondsQuotedOnTheMarketValuation());

        Optional<BigDecimal> bondsQuantity = Optional.ofNullable(
                bondsQuotedService.findByType(bondsQuotedValuation.getType()).getQuantity());

        bondsQuotedValuation.setEntireValuation(bondsQuantity.orElse(BigDecimal.ZERO).multiply(oneBondPrice));

        bondsQuotedValuationService.saveBondsQuotedValuation(bondsQuotedValuation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bonds/quoted/{type}")
    public BondsQuotedOnTheMarketDto getInvestmentInfo(@PathVariable String type) {
        return bondsQuotedMapper.mapToBondsQuotedInvestmentDto(bondsQuotedService.findByType(type));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bonds/quoted")
    public List<BondsQuotedValuationDto> getAllValuations() {
        return bondsQuotedMapper.mapToBondsQuotedListDto(bondsQuotedValuationService.getAll());
    }
}
