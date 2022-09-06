package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.DevelopedMarketStocksClient;
import com.restapi.financialfortressbackend.client.ExchangeClient;
import com.restapi.financialfortressbackend.domain.DevelopedMarketStocksValuation;
import com.restapi.financialfortressbackend.domain.dto.DevelopedMarketStocksDto;
import com.restapi.financialfortressbackend.domain.dto.DevelopedMarketValuationDto;

import com.restapi.financialfortressbackend.mapper.DevelopedMarketStocksMapper;
import com.restapi.financialfortressbackend.service.DevelopedMarketStocksService;
import com.restapi.financialfortressbackend.service.DevelopedMarketValuationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController()
@CrossOrigin(value = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class DevelopedMarketStocksController {

    private final DevelopedMarketStocksClient developedMarketClient;
    private final DevelopedMarketStocksService developedMarketService;
    private final DevelopedMarketValuationService developedMarketValuationService;
    private final DevelopedMarketStocksMapper developedMarketMapper;
    private final ExchangeClient exchangeClient;

    @Scheduled(cron = "0 0 10 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "/developed/value")
    public void saveNewValuation() {

        DevelopedMarketStocksValuation developedMarketValuation = new DevelopedMarketStocksValuation();

        developedMarketValuation.setDate(LocalDate.now());
        BigDecimal oneSharePrice = developedMarketValuationService
                .getOneShareValue(developedMarketClient.getDayStockValuation(), exchangeClient.getUSDToPLN());
        developedMarketValuation.setValuation(oneSharePrice);
        developedMarketValuation.setCommissionRate(developedMarketClient.getCommissionValue());

        Optional<BigDecimal> sharesQuantity = Optional.ofNullable(
                developedMarketService.findByType(developedMarketValuation.getType()).getQuantity());

        developedMarketValuation.setEntireValuation(sharesQuantity.orElse(BigDecimal.ZERO).multiply(oneSharePrice));

        developedMarketValuationService.saveDevelopedMarketValuation(developedMarketValuation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/developed/invest/{type}")
    public DevelopedMarketStocksDto getInvestmentInfo(@PathVariable String type) {
        return developedMarketMapper.mapToDevelopedInvestmentDto(developedMarketService.findByType(type));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/developed/value")
    public List<DevelopedMarketValuationDto> getAllValuations() {
        return developedMarketMapper.mapToDevelopedValuationListDto(developedMarketValuationService.getAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/developed/values")
    public List<BigDecimal> getYearPrices() {
        return developedMarketClient.getYearStockValuation();
    }

}
