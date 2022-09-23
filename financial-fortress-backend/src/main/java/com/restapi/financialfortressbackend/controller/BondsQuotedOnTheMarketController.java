package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.FastTrackClient;
import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import com.restapi.financialfortressbackend.domain.investment.BondsQuotedOnTheMarketInvestment;
import com.restapi.financialfortressbackend.domain.investment.SimpleInvestment;
import com.restapi.financialfortressbackend.domain.valuation.BondsQuotedOnTheMarketValuation;
import com.restapi.financialfortressbackend.domain.investment.dto.BondsQuotedOnTheMarketDto;
import com.restapi.financialfortressbackend.domain.valuation.SimpleValuation;
import com.restapi.financialfortressbackend.domain.valuation.dto.BondsQuotedValuationDto;
import com.restapi.financialfortressbackend.mapper.BondsQuotedOnTheMarketMapper;
import com.restapi.financialfortressbackend.service.valuation.SavingNewValuationsService;
import com.restapi.financialfortressbackend.service.investment.BondsQuotedOnTheMarketService;
import com.restapi.financialfortressbackend.service.investment.SavingNewInvestmentService;
import com.restapi.financialfortressbackend.service.valuation.BondsQuotedValuationService;
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
public class BondsQuotedOnTheMarketController {

    private final BondsQuotedOnTheMarketMapper bondsQuotedMapper;
    private final BondsQuotedOnTheMarketService bondsQuotedService;
    private final BondsQuotedValuationService bondsQuotedValuationService;
    private final ModelPortfolioService modelPortfolioService;
    private final FastTrackClient fastTrackClient;
    private final SavingNewValuationsService valuationsService;
    private final SavingNewInvestmentService investmentService;

    @Scheduled(cron = "0 0 10,18 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "/bonds/quoted/value")
    public void saveNewValuation() {

        BondsQuotedOnTheMarketValuation bondsQuotedValuation = new BondsQuotedOnTheMarketValuation();
        BigDecimal oneBondPrice = fastTrackClient.getActualValidation(
                bondsQuotedValuation.getType()).add(BigDecimal.valueOf(1000));
        BigDecimal commissionRate = fastTrackClient.getCommissionRate();
        SimpleValuation simpleValuation = valuationsService
                .getNewValue(bondsQuotedValuation, oneBondPrice, commissionRate);
        BondsQuotedOnTheMarketValuation bondsValuation = (BondsQuotedOnTheMarketValuation) simpleValuation;

        if(modelPortfolioService.getAll().size()!=0) {
            BondsQuotedOnTheMarketInvestment bondsQuotedInvestment = new BondsQuotedOnTheMarketInvestment();
            BondsQuotedOnTheMarketInvestment lastBondsInvestment = bondsQuotedService.findTopByDate();
            BigDecimal bondsQuantity = bondsQuotedService
                    .findByType(bondsQuotedValuation.getType())
                    .getQuantity();
            BigDecimal entireValuation = bondsQuantity.multiply(oneBondPrice);

            SimpleInvestment simpleInvestment = investmentService
                    .getNewInvestment(bondsQuotedInvestment, lastBondsInvestment, entireValuation);
            BondsQuotedOnTheMarketInvestment bondsInvestment = (BondsQuotedOnTheMarketInvestment) simpleInvestment;

            bondsQuotedService.saveInvestment(bondsInvestment);
        }
        bondsQuotedValuationService.saveBondsQuotedValuation(bondsValuation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bonds/quoted")
    public List<BondsQuotedOnTheMarketDto> getInvestmentInfo() {
        return bondsQuotedMapper.mapToBondsQuotedInvestmentListDto(bondsQuotedService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bonds/quoted/value")
    public List<BondsQuotedValuationDto> getAllValuations() {
        return bondsQuotedMapper.mapToBondsQuotedListDto(bondsQuotedValuationService.getAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bonds/values")
    public List<BigDecimal> getYearPrices() {
        return fastTrackClient
                .getHistoricalValuation(InvestmentInstrumentName.BONDS_QUOTED.getName());
    }
}
