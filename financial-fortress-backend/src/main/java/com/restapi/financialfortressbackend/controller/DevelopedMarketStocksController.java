package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.ExchangeClient;
import com.restapi.financialfortressbackend.client.FastTrackClient;
import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import com.restapi.financialfortressbackend.domain.investment.DevelopedMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.investment.SimpleInvestment;
import com.restapi.financialfortressbackend.domain.valuation.DevelopedMarketStocksValuation;
import com.restapi.financialfortressbackend.domain.investment.dto.DevelopedMarketStocksDto;
import com.restapi.financialfortressbackend.domain.valuation.SimpleValuation;
import com.restapi.financialfortressbackend.domain.valuation.dto.DevelopedMarketValuationDto;

import com.restapi.financialfortressbackend.mapper.DevelopedMarketStocksMapper;
import com.restapi.financialfortressbackend.service.valuation.SavingNewValuationsService;
import com.restapi.financialfortressbackend.service.investment.DevelopedMarketStocksService;
import com.restapi.financialfortressbackend.service.investment.SavingNewInvestmentService;
import com.restapi.financialfortressbackend.service.valuation.DevelopedMarketValuationService;
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
public class DevelopedMarketStocksController {

    private final DevelopedMarketStocksService developedMarketService;
    private final DevelopedMarketValuationService developedMarketValuationService;
    private final DevelopedMarketStocksMapper developedMarketMapper;
    private final ExchangeClient exchangeClient;
    private final ModelPortfolioService modelPortfolioService;
    private final FastTrackClient fastTrackClient;

    private final SavingNewValuationsService valuationsService;
    private final SavingNewInvestmentService investmentService;

    @Scheduled(cron = "0 0 11,19 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "/developed/value")
    public void saveNewValuation() {
        DevelopedMarketStocksValuation developedMarketValuation = new DevelopedMarketStocksValuation();
        BigDecimal oneSharePrice = developedMarketValuationService
                .getOneShareValue(fastTrackClient
                        .getActualValidation(developedMarketValuation.getType()), exchangeClient.getUSDToPLN());
        BigDecimal commissionRate = fastTrackClient.getCommissionRate();
        SimpleValuation simpleValuation = valuationsService
                .getNewValue(developedMarketValuation, oneSharePrice, commissionRate);

        DevelopedMarketStocksValuation developedValuation = (DevelopedMarketStocksValuation) simpleValuation;

        if(modelPortfolioService.getAll().size()!=0) {
            DevelopedMarketStocksInvestment developedMarketInvestment = new DevelopedMarketStocksInvestment();
            DevelopedMarketStocksInvestment lastDevelopedInvestment = developedMarketService.findTopByDate();
            BigDecimal sharesQuantity = developedMarketService
                    .findByType(developedMarketValuation.getType())
                    .getQuantity();
            BigDecimal entireValuation = sharesQuantity.multiply(oneSharePrice);

            SimpleInvestment simpleInvestment = investmentService
                    .getNewInvestment(developedMarketInvestment, lastDevelopedInvestment, entireValuation);
            DevelopedMarketStocksInvestment developedInvestment = (DevelopedMarketStocksInvestment) simpleInvestment;

            developedMarketService.saveInvestment(developedInvestment);
        }
        developedMarketValuationService.saveDevelopedMarketValuation(developedValuation);
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
        return fastTrackClient
                .getHistoricalValuation(InvestmentInstrumentName.DEVELOPED_ETF.getName());
    }
}
