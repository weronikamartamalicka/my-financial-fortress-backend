package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.ExchangeClient;
import com.restapi.financialfortressbackend.client.FastTrackClient;
import com.restapi.financialfortressbackend.domain.DevelopedMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.DevelopedMarketStocksValuation;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.domain.dto.DevelopedMarketStocksDto;
import com.restapi.financialfortressbackend.domain.dto.DevelopedMarketValuationDto;

import com.restapi.financialfortressbackend.mapper.DevelopedMarketStocksMapper;
import com.restapi.financialfortressbackend.service.DevelopedMarketStocksService;
import com.restapi.financialfortressbackend.service.DevelopedMarketValuationService;
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
public class DevelopedMarketStocksController {

    private final DevelopedMarketStocksService developedMarketService;
    private final DevelopedMarketValuationService developedMarketValuationService;
    private final DevelopedMarketStocksMapper developedMarketMapper;
    private final ExchangeClient exchangeClient;
    private final ModelPortfolioService modelPortfolioService;
    private final FastTrackClient fastTrackClient;

    @Scheduled(cron = "0 0 11,19 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "/developed/value")
    public void saveNewValuation() {

        ZoneId z = ZoneId.of( "Europe/Warsaw");

        DevelopedMarketStocksValuation developedMarketValuation = new DevelopedMarketStocksValuation();
        developedMarketValuation.setDate(LocalDateTime.now(z));
        BigDecimal oneSharePrice = developedMarketValuationService
                .getOneShareValue(fastTrackClient
                        .getActualValidation(developedMarketValuation.getType()), exchangeClient.getUSDToPLN());
        developedMarketValuation.setValuation(oneSharePrice);
        developedMarketValuation.setCommissionRate(fastTrackClient.getCommissionRate());

        if(modelPortfolioService.getAll().size()!=0) {
            DevelopedMarketStocksInvestment developedMarketInvestment = new DevelopedMarketStocksInvestment();
            developedMarketInvestment.setDate(LocalDateTime.now(z));
            DevelopedMarketStocksInvestment lastDevelopedInvestment = developedMarketService.findTopByDate();
            developedMarketInvestment.setQuantity(lastDevelopedInvestment.getQuantity());

            ModelPortfolioInvestment modelPortfolio = new ModelPortfolioInvestment();
            modelPortfolio.setDate(LocalDateTime.now(z));
            modelPortfolio = modelPortfolioService.copyPortfolioValues(modelPortfolio);

            BigDecimal sharesQuantity = developedMarketService
                    .findByType(developedMarketValuation.getType())
                    .getQuantity();

            developedMarketInvestment.setEntireValuation(sharesQuantity.multiply(oneSharePrice));
            modelPortfolio.setDevelopedMarketValue(sharesQuantity.multiply(oneSharePrice));
            modelPortfolio = modelPortfolioService.calculatePercentageComposition(modelPortfolio);

            developedMarketService.saveDevelopedMarketInvestment(developedMarketInvestment);
            modelPortfolioService.saveModelPortfolio(modelPortfolio);
        }
        developedMarketValuationService.saveDevelopedMarketValuation(developedMarketValuation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/developed/invest")
    public List<DevelopedMarketStocksDto> getInvestmentInfo() {
        return developedMarketMapper.mapToDevelopedInvestmentListDto(developedMarketService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/developed/value")
    public List<DevelopedMarketValuationDto> getAllValuations() {
        return developedMarketMapper.mapToDevelopedValuationListDto(developedMarketValuationService.getAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/developed/values")
    public List<BigDecimal> getYearPrices() {
        return fastTrackClient.getHistoricalValuation("MSCI China A DivAdj Ix");
    }
}
