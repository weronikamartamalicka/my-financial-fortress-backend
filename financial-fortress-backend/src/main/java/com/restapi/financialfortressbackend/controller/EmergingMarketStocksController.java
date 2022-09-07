package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.EmergingMarketStocksClient;
import com.restapi.financialfortressbackend.client.ExchangeClient;
import com.restapi.financialfortressbackend.domain.EmergingMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.EmergingMarketStocksValuation;
import com.restapi.financialfortressbackend.domain.dto.EmergingMarketStocksDto;
import com.restapi.financialfortressbackend.domain.dto.EmergingMarketValuationDto;
import com.restapi.financialfortressbackend.mapper.EmergingMarketStocksMapper;
import com.restapi.financialfortressbackend.service.EmergingMarketStocksService;
import com.restapi.financialfortressbackend.service.EmergingMarketValuationService;
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
public class EmergingMarketStocksController {

    private final EmergingMarketValuationService emergingValuationService;
    private final EmergingMarketStocksService emergingMarketService;
    private final EmergingMarketStocksClient emergingMarketClient;
    private final EmergingMarketStocksMapper emergingMarketMapper;
    private final ExchangeClient exchangeClient;
    private final ModelPortfolioService modelPortfolioService;

    @Scheduled(cron = "0 0 10 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "/emerging/value")
    public void saveNewValuation() {

        EmergingMarketStocksValuation emergingMarketValuation = new EmergingMarketStocksValuation();

        emergingMarketValuation.setDate(LocalDateTime.now());
        BigDecimal oneSharePrice = emergingValuationService
                .getOneShareValue(emergingMarketClient.getDayStockValuation(), exchangeClient.getUSDToPLN());
        emergingMarketValuation.setValuation(oneSharePrice);
        emergingMarketValuation.setCommissionRate(emergingMarketClient.getCommissionValue());

        BigDecimal sharesQuantity = emergingMarketService
                .findByType(emergingMarketValuation.getType())
                .orElse(new EmergingMarketStocksInvestment(BigDecimal.valueOf(0)))
                .getQuantity();

        emergingMarketValuation.setEntireValuation(sharesQuantity.multiply(oneSharePrice));
        modelPortfolioService.findByDate(LocalDate.now()).setEmergingMarketValue(sharesQuantity.multiply(oneSharePrice));
        modelPortfolioService.calculatePercentageComposition();

        emergingValuationService.saveDevelopedMarketValuation(emergingMarketValuation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/emerging/invest/{type}")
    public EmergingMarketStocksDto getInvestmentInfo(@PathVariable String type) {
        return emergingMarketMapper.mapToEmergingInvestmentDto(emergingMarketService.findByType(type).get());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/emerging/value")
    public List<EmergingMarketValuationDto> getAllValuations() {
        return emergingMarketMapper.mapToEmergingValuationListDto(emergingValuationService.getAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/emerging/values")
    public List<BigDecimal> getYearPrices() {
        return emergingMarketClient.getYearStockValuation();
    }
}
