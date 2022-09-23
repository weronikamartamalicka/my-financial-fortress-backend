package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.ExchangeClient;
import com.restapi.financialfortressbackend.client.FastTrackClient;
import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import com.restapi.financialfortressbackend.domain.investment.EmergingMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.investment.SimpleInvestment;
import com.restapi.financialfortressbackend.domain.valuation.EmergingMarketStocksValuation;
import com.restapi.financialfortressbackend.domain.investment.dto.EmergingMarketStocksDto;
import com.restapi.financialfortressbackend.domain.valuation.SimpleValuation;
import com.restapi.financialfortressbackend.domain.valuation.dto.EmergingMarketValuationDto;
import com.restapi.financialfortressbackend.mapper.EmergingMarketStocksMapper;
import com.restapi.financialfortressbackend.service.valuation.SavingNewValuationsService;
import com.restapi.financialfortressbackend.service.investment.EmergingMarketStocksService;
import com.restapi.financialfortressbackend.service.investment.SavingNewInvestmentService;
import com.restapi.financialfortressbackend.service.valuation.EmergingMarketValuationService;
import com.restapi.financialfortressbackend.service.ModelPortfolioService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    private final SavingNewValuationsService valuationsService;
    private final SavingNewInvestmentService investmentService;

    @Scheduled(cron = "0 0 12,20 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "/emerging/value")
    public void saveNewValuation() {

        EmergingMarketStocksValuation emergingMarketValuation = new EmergingMarketStocksValuation();
        BigDecimal oneSharePrice = emergingValuationService
                .getOneShareValue(fastTrackClient
                        .getActualValidation(emergingMarketValuation.getType()), exchangeClient.getUSDToPLN());
        BigDecimal commissionRate = fastTrackClient.getCommissionRate();

        SimpleValuation simpleValuation = valuationsService
                .getNewValue(emergingMarketValuation, oneSharePrice, commissionRate);

        EmergingMarketStocksValuation emergingValuation = (EmergingMarketStocksValuation) simpleValuation;

        if(modelPortfolioService.getAll().size()!=0) {
            EmergingMarketStocksInvestment emergingMarketInvestment = new EmergingMarketStocksInvestment();
            EmergingMarketStocksInvestment lastEmergingInvestment = emergingMarketService.findTopByDate();
            BigDecimal sharesQuantity = emergingMarketService
                    .findByType(emergingMarketValuation.getType())
                    .getQuantity();
            BigDecimal entireValue = sharesQuantity.multiply(oneSharePrice);

            SimpleInvestment simpleInvestment = investmentService
                    .getNewInvestment(emergingMarketInvestment, lastEmergingInvestment, entireValue);

            EmergingMarketStocksInvestment emergingInvestment = (EmergingMarketStocksInvestment) simpleInvestment;

            emergingMarketService.saveInvestment(emergingInvestment);
        }
        emergingValuationService.saveEmergingMarketValuation(emergingValuation);
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
        return fastTrackClient.getHistoricalValuation(InvestmentInstrumentName.EMERGING_ETF.getName());
    }
}
