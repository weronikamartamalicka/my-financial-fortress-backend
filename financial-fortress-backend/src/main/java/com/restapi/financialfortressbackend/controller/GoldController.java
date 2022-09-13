package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.GoldClient;
import com.restapi.financialfortressbackend.domain.GoldInvestment;
import com.restapi.financialfortressbackend.domain.GoldValuation;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.domain.dto.*;
import com.restapi.financialfortressbackend.domain.dto.response.Root;
import com.restapi.financialfortressbackend.mapper.GoldMapper;
import com.restapi.financialfortressbackend.service.GoldInvestmentService;
import com.restapi.financialfortressbackend.service.GoldValuationService;
import com.restapi.financialfortressbackend.service.ModelPortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController()
@CrossOrigin(value = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class GoldController {

    private final GoldClient goldClient;
    private final GoldValuationService goldValuationService;
    private final GoldInvestmentService goldInvestmentService;
    private final GoldMapper goldMapper;
    private final ModelPortfolioService modelPortfolioService;

    @Scheduled(cron = "0 0 09,14 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "/gold/value")
    public void saveNewValuation() {

        GoldValuation goldValuation = new GoldValuation();
        goldValuation.setDate(LocalDateTime.now());
        Root goldResponse = goldClient.getGoldSaleValue();
        BigDecimal oneCoinPrice = goldValuationService.getOneCoinValue(goldResponse);
        goldValuation.setOneCoinPrice(oneCoinPrice);

        if(modelPortfolioService.getAll().size()!=0) {
            GoldInvestment goldInvestment = new GoldInvestment();
            goldInvestment.setDate(LocalDateTime.now());
            GoldInvestment lastGoldInvestment = goldInvestmentService.findTopByDate();
            goldInvestment.setQuantity(lastGoldInvestment.getQuantity());

            ModelPortfolioInvestment modelPortfolio = new ModelPortfolioInvestment();
            modelPortfolio.setDate(LocalDateTime.now());
            modelPortfolio = modelPortfolioService.copyPortfolioValues(modelPortfolio);

            BigDecimal coinsQuantity = goldInvestmentService.findByType(goldValuation.getTYPE()).getQuantity();

            goldInvestment.setEntireValuation(coinsQuantity.multiply(oneCoinPrice));
            modelPortfolio.setGoldValue(coinsQuantity.multiply(oneCoinPrice));
            modelPortfolio = modelPortfolioService.calculatePercentageComposition(modelPortfolio);

            goldInvestmentService.saveGoldInvestment(goldInvestment);
            modelPortfolioService.saveModelPortfolio(modelPortfolio);
        }

        goldValuationService.saveGoldValuation(goldValuation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gold/invest")
    public List<GoldInvestmentDto> getInvestmentInfo() {
        return goldMapper.matToGoldInvestmentListDto(goldInvestmentService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gold/value")
    public List<GoldValuationDto> getAllValuations() {
        return goldMapper.matToGoldListDto(goldValuationService.getAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gold/values")
    public List<BigDecimal> getYearPrices() {
        return goldClient.getYearGoldSaleValue();
    }

}
