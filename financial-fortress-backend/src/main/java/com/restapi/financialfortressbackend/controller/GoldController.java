package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.GoldClient;
import com.restapi.financialfortressbackend.domain.investment.GoldInvestment;
import com.restapi.financialfortressbackend.domain.investment.SimpleInvestment;
import com.restapi.financialfortressbackend.domain.investment.dto.GoldInvestmentDto;
import com.restapi.financialfortressbackend.domain.valuation.GoldValuation;
import com.restapi.financialfortressbackend.domain.valuation.SimpleValuation;
import com.restapi.financialfortressbackend.domain.valuation.dto.GoldValuationDto;
import com.restapi.financialfortressbackend.mapper.GoldMapper;
import com.restapi.financialfortressbackend.service.valuation.SavingNewValuationsService;
import com.restapi.financialfortressbackend.service.investment.GoldInvestmentService;
import com.restapi.financialfortressbackend.service.investment.SavingNewInvestmentService;
import com.restapi.financialfortressbackend.service.valuation.GoldValuationService;
import com.restapi.financialfortressbackend.service.ModelPortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    private final SavingNewValuationsService valuationsService;
    private final SavingNewInvestmentService investmentService;

    @Scheduled(cron = "0 0 09,14 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "/gold/value")
    public void saveNewValuation() {

        GoldValuation goldValuation = new GoldValuation();
        BigDecimal oneCoinPrice = goldValuationService.getOneCoinValue(goldClient.getGoldSaleValue());

        SimpleValuation simpleValuation = valuationsService.getNewValue(goldValuation, oneCoinPrice);
        GoldValuation gold = (GoldValuation) simpleValuation;

        if(modelPortfolioService.getAll().size()!=0) {
            GoldInvestment goldInvestment = new GoldInvestment();
            GoldInvestment lastGoldInvestment = goldInvestmentService.findTopByDate();
            BigDecimal coinsQuantity = goldInvestmentService.findByType(goldValuation.getType()).getQuantity();
            BigDecimal entireValue = coinsQuantity.multiply(oneCoinPrice);

            SimpleInvestment simpleInvestment = investmentService
                    .getNewInvestment(goldInvestment, lastGoldInvestment, entireValue);

            GoldInvestment goldInvest = (GoldInvestment) simpleInvestment;

            goldInvestmentService.saveInvestment(goldInvest);
        }
        goldValuationService.saveGoldValuation(gold);
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
