package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.EmergingMarketStocksClient;
import com.restapi.financialfortressbackend.client.ExchangeClient;
import com.restapi.financialfortressbackend.domain.EmergingMarketStocksValuation;
import com.restapi.financialfortressbackend.domain.dto.EmergingMarketStocksDto;
import com.restapi.financialfortressbackend.domain.dto.EmergingMarketValuationDto;
import com.restapi.financialfortressbackend.mapper.EmergingMarketStocksMapper;
import com.restapi.financialfortressbackend.service.EmergingMarketStocksService;
import com.restapi.financialfortressbackend.service.EmergingMarketValuationService;
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
public class EmergingMarketStocksController {

    private final EmergingMarketValuationService emergingValuationService;
    private final EmergingMarketStocksService emergingMarketService;
    private final EmergingMarketStocksClient emergingMarketClient;
    private final EmergingMarketStocksMapper emergingMarketMapper;
    private final ExchangeClient exchangeClient;

    @Scheduled(cron = "0 0 10 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "/emerging/value")
    public void saveNewValuation() {

        EmergingMarketStocksValuation emergingMarketValuation = new EmergingMarketStocksValuation();

        emergingMarketValuation.setDate(LocalDate.now());
        BigDecimal oneSharePrice = emergingValuationService
                .getOneShareValue(emergingMarketClient.getDayStockValuation(), exchangeClient.getUSDToPLN());
        emergingMarketValuation.setValuation(oneSharePrice);
        emergingMarketValuation.setCommissionRate(emergingMarketClient.getCommissionValue());

        Optional<BigDecimal> sharesQuantity = Optional.ofNullable(
                emergingMarketService.findByType(emergingMarketValuation.getType()).getQuantity());

        emergingMarketValuation.setEntireValuation(sharesQuantity.orElse(BigDecimal.ZERO).multiply(oneSharePrice));

        emergingValuationService.saveDevelopedMarketValuation(emergingMarketValuation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/emerging/invest/{type}")
    public EmergingMarketStocksDto getInvestmentInfo(@PathVariable String type) {
        return emergingMarketMapper.mapToEmergingInvestmentDto(emergingMarketService.findByType(type));
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
