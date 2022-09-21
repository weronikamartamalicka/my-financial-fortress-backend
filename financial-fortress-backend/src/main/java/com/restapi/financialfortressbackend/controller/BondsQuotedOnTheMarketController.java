package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.FastTrackClient;
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
import java.time.ZoneId;
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

    @Scheduled(cron = "0 0 10,18 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "/bonds/quoted/value")
    public void saveNewValuation() {

        ZoneId z = ZoneId.of( "Europe/Warsaw" );

        BondsQuotedOnTheMarketValuation bondsQuotedValuation = new BondsQuotedOnTheMarketValuation();
        bondsQuotedValuation.setDate(LocalDateTime.now(z));
        BigDecimal oneBondPrice = fastTrackClient.getActualValidation(
                bondsQuotedValuation.getType()).add(BigDecimal.valueOf(1000));
        bondsQuotedValuation.setValuation(oneBondPrice);
        bondsQuotedValuation.setCommissionRate(fastTrackClient.getCommissionRate());

        if(modelPortfolioService.getAll().size()!=0) {
            BondsQuotedOnTheMarketInvestment bondsQuotedInvestment = new BondsQuotedOnTheMarketInvestment();
            bondsQuotedInvestment.setDate(LocalDateTime.now(z));
            BondsQuotedOnTheMarketInvestment lastBondsInvestment = bondsQuotedService.findTopByDate();
            bondsQuotedInvestment.setRedemptionDate(lastBondsInvestment.getRedemptionDate());
            bondsQuotedInvestment.setQuantity(lastBondsInvestment.getQuantity());

            ModelPortfolioInvestment modelPortfolio = new ModelPortfolioInvestment();
            modelPortfolio.setDate(LocalDateTime.now(z));
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
        return fastTrackClient.getHistoricalValuation("10 Yr Gov Bond iShr Ix");
    }
}
