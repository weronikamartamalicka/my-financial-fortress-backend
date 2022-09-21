package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.ExchangeClient;
import com.restapi.financialfortressbackend.client.FastTrackClient;
import com.restapi.financialfortressbackend.domain.EmergingMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.EmergingMarketStocksValuation;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController()
@CrossOrigin(value = "*")
@AllArgsConstructor
@RequestMapping(value = "/v1")
public class EmergingMarketStocksController {

    private final EmergingMarketValuationService emergingValuationService;
    private final EmergingMarketStocksService emergingMarketService;
    private final EmergingMarketStocksMapper emergingMarketMapper;
    private final ExchangeClient exchangeClient;
    private final ModelPortfolioService modelPortfolioService;
    private final FastTrackClient fastTrackClient;

    @Scheduled(cron = "0 0 12,20 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "/emerging/value")
    public void saveNewValuation() {

        ZoneId z = ZoneId.of( "Europe/Warsaw");

        EmergingMarketStocksValuation emergingMarketValuation = new EmergingMarketStocksValuation();
        emergingMarketValuation.setDate(LocalDateTime.now(z));
        BigDecimal oneSharePrice = emergingValuationService
                .getOneShareValue(fastTrackClient
                        .getActualValidation(emergingMarketValuation.getType()), exchangeClient.getUSDToPLN());
        emergingMarketValuation.setValuation(oneSharePrice);
        emergingMarketValuation.setCommissionRate(fastTrackClient.getCommissionRate());

        if(modelPortfolioService.getAll().size()!=0) {
            EmergingMarketStocksInvestment emergingMarketInvestment = new EmergingMarketStocksInvestment();
            emergingMarketInvestment.setDate(LocalDateTime.now(z));
            EmergingMarketStocksInvestment lastEmergingInvestment = emergingMarketService.findTopByDate();
            emergingMarketInvestment.setQuantity(lastEmergingInvestment.getQuantity());

            ModelPortfolioInvestment modelPortfolio = new ModelPortfolioInvestment();
            modelPortfolio.setDate(LocalDateTime.now(z));
            modelPortfolio = modelPortfolioService.copyPortfolioValues(modelPortfolio);

            BigDecimal sharesQuantity = emergingMarketService
                    .findByType(emergingMarketValuation.getType())
                    .getQuantity();

            emergingMarketInvestment.setEntireValuation(sharesQuantity.multiply(oneSharePrice));
            modelPortfolio.setEmergingMarketValue(sharesQuantity.multiply(oneSharePrice));
            modelPortfolio = modelPortfolioService.calculatePercentageComposition(modelPortfolio);

            emergingMarketService.saveEmergingMarketInvestment(emergingMarketInvestment);
            modelPortfolioService.saveModelPortfolio(modelPortfolio);
        }
        emergingValuationService.saveEmergingMarketValuation(emergingMarketValuation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/emerging/invest")
    public List<EmergingMarketStocksDto> getInvestmentInfo() {
        return emergingMarketMapper.mapToEmergingInvestmentListDto(emergingMarketService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/emerging/value")
    public List<EmergingMarketValuationDto> getAllValuations() {
        return emergingMarketMapper.mapToEmergingValuationListDto(emergingValuationService.getAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/emerging/values")
    public List<BigDecimal> getYearPrices() {
        return fastTrackClient.getHistoricalValuation("BofAML AAA-A Emerging Markets Corporate Ix");
    }
}
