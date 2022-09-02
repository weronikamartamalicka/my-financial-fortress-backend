package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.GoldClient;
import com.restapi.financialfortressbackend.domain.GoldValuation;
import com.restapi.financialfortressbackend.domain.dto.GoldInvestmentDto;
import com.restapi.financialfortressbackend.domain.dto.GoldValuationDto;
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
        BigDecimal oneCoinPrice = goldValuationService.getOneCoinValue(goldClient.getGoldSaleValue());
        goldValuation.setOneCoinPrice(oneCoinPrice);
        goldValuation.setMarketPrice(BigDecimal.valueOf(goldClient.getGoldSaleValue().getRates().getXAU()));

        Optional<BigDecimal> coinsQuantity = Optional.ofNullable(
                goldInvestmentService.findByType(goldValuation.getTYPE()).getQuantity());

        goldValuation.setEntireValuation(coinsQuantity.orElse(BigDecimal.ZERO).multiply(oneCoinPrice));

        goldValuationService.saveGoldValuation(goldValuation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gold/invest/{type}")
    public GoldInvestmentDto getInvestmentInfo(@PathVariable String type) {
        return goldMapper.matToGoldInvestmentDto(goldInvestmentService.findByType(type));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gold/value")
    public List<GoldValuationDto> getAllValuations() {
        return goldMapper.matToGoldListDto(goldValuationService.getAll());
    }

}
