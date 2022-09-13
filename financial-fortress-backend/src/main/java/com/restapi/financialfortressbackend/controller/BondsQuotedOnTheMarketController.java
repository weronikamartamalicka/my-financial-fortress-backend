package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.BondsQuotedOnTheMarketClient;
import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketInvestment;
import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketValuation;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.domain.dto.BondsQuotedOnTheMarketDto;
import com.restapi.financialfortressbackend.domain.dto.BondsQuotedValuationDto;
import com.restapi.financialfortressbackend.mapper.BondsQuotedOnTheMarketMapper;
import com.restapi.financialfortressbackend.service.BondsQuotedOnTheMarketService;
import com.restapi.financialfortressbackend.service.BondsQuotedValuationService;
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
public class BondsQuotedOnTheMarketController {

    private final BondsQuotedOnTheMarketMapper bondsQuotedMapper;
    private final BondsQuotedOnTheMarketClient bondsQuotedClient;
    private final BondsQuotedOnTheMarketService bondsQuotedService;
    private final BondsQuotedValuationService bondsQuotedValuationService;
    private final ModelPortfolioService modelPortfolioService;

    @Scheduled(cron = "0 0 10,18 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "/bonds/quoted/value")
    public void saveNewValuation() {

        BondsQuotedOnTheMarketValuation bondsQuotedValuation = new BondsQuotedOnTheMarketValuation();
        bondsQuotedValuation.setDate(LocalDateTime.now());
        BigDecimal oneBondPrice = bondsQuotedClient.getDayBondsValuation().add(BigDecimal.valueOf(1000));
        bondsQuotedValuation.setValuation(oneBondPrice);
        bondsQuotedValuation.setCommissionRate(bondsQuotedClient.getCommissionValue());

        if(modelPortfolioService.getAll().size()!=0) {
            BondsQuotedOnTheMarketInvestment bondsQuotedInvestment = new BondsQuotedOnTheMarketInvestment();
            bondsQuotedInvestment.setDate(LocalDateTime.now());
            BondsQuotedOnTheMarketInvestment lastBondsInvestment = bondsQuotedService.findTopByDate();
            bondsQuotedInvestment.setRedemptionDate(lastBondsInvestment.getRedemptionDate());
            bondsQuotedInvestment.setQuantity(lastBondsInvestment.getQuantity());

            ModelPortfolioInvestment modelPortfolio = new ModelPortfolioInvestment();
            modelPortfolio.setDate(LocalDateTime.now());
            modelPortfolio = modelPortfolioService.copyPortfolioValues(modelPortfolio);

            BigDecimal bondsQuantity = bondsQuotedService
                    .findByType(bondsQuotedValuation.getType())
                    .getQuantity();

            bondsQuotedInvestment.setEntireValuation(bondsQuantity.multiply(oneBondPrice));
            modelPortfolio.setBondsQuotedValue(bondsQuantity.multiply(oneBondPrice));

            modelPortfolio = modelPortfolioService.calculatePercentageComposition(modelPortfolio);
            bondsQuotedService.saveBondsQuotedInvestment(bondsQuotedInvestment);
            modelPortfolioService.saveModelPortfolio(modelPortfolio);
        }

        bondsQuotedValuationService.saveBondsQuotedValuation(bondsQuotedValuation);
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
        return bondsQuotedClient.getYearBondsValuation();
    }
}
