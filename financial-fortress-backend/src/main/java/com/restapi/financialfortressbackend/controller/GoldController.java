package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.GoldClient;
import com.restapi.financialfortressbackend.domain.GoldInvestment;
import com.restapi.financialfortressbackend.domain.GoldValuation;
import com.restapi.financialfortressbackend.domain.dto.*;
import com.restapi.financialfortressbackend.mapper.GoldMapper;
import com.restapi.financialfortressbackend.service.GoldInvestmentService;
import com.restapi.financialfortressbackend.service.GoldValuationService;
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
public class GoldController {

    private final GoldClient goldClient;
    private final GoldValuationService goldValuationService;
    private final GoldInvestmentService goldInvestmentService;
    private final GoldMapper goldMapper;

    @Scheduled(cron = "0 0 10 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "/gold/value")
    public void saveNewValuation() {

        GoldValuation goldValuation = new GoldValuation();

        goldValuation.setDate(LocalDate.now());
        Root goldResponse = goldClient.getGoldSaleValue();
        BigDecimal oneCoinPrice = goldValuationService.getOneCoinValue(goldResponse);
        goldValuation.setOneCoinPrice(oneCoinPrice);

        BigDecimal coinsQuantity = goldInvestmentService.findByType(goldValuation.getTYPE())
                        .orElse(new GoldInvestment(new BigDecimal(0))).getQuantity();

        goldValuation.setEntireValuation(coinsQuantity.multiply(oneCoinPrice));

        goldValuationService.saveGoldValuation(goldValuation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gold/invest/{type}")
    public GoldInvestmentDto getInvestmentInfo(@PathVariable String type) {
        return goldMapper.matToGoldInvestmentDto(goldInvestmentService.findByType(type)
                .orElse(new GoldInvestment(new BigDecimal(0))));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gold/value")
    public List<GoldValuationDto> getAllValuations() {
        return goldMapper.matToGoldListDto(goldValuationService.getAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gold/values")
    public List<RatesMap> getYearPrices() {
        return goldClient.getYearGoldSaleValue();
    }

}
