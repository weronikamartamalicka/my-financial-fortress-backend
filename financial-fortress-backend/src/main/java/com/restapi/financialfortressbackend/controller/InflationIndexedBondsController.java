package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.InflationClient;
import com.restapi.financialfortressbackend.domain.investment.InflationIndexedBondsInvestment;
import com.restapi.financialfortressbackend.domain.investment.SimpleInvestment;
import com.restapi.financialfortressbackend.domain.valuation.InflationIndexedBondsValuation;
import com.restapi.financialfortressbackend.domain.investment.dto.InflationIndexedBondsDto;
import com.restapi.financialfortressbackend.domain.valuation.SimpleValuation;
import com.restapi.financialfortressbackend.domain.valuation.dto.InflationValuationDto;
import com.restapi.financialfortressbackend.mapper.InflationMapper;
import com.restapi.financialfortressbackend.service.valuation.SavingNewValuationsService;
import com.restapi.financialfortressbackend.service.investment.InflationIndexedBondsService;
import com.restapi.financialfortressbackend.service.investment.SavingNewInvestmentService;
import com.restapi.financialfortressbackend.service.valuation.InflationValuationService;
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
public class InflationIndexedBondsController {
    private final InflationClient inflationClient;
    private final InflationValuationService inflationValuationService;
    private final InflationIndexedBondsService inflationIndexedBondsService;
    private final InflationMapper inflationMapper;
    private final ModelPortfolioService modelPortfolioService;

    private final SavingNewValuationsService valuationsService;
    private final SavingNewInvestmentService investmentService;

    @Scheduled(cron = "0 0 10 15 03 *")
    @RequestMapping(method = RequestMethod.POST, value = "/inflation/value")
    public void saveNewValuation() {

        InflationIndexedBondsValuation inflationIndexedBondsValuation = new InflationIndexedBondsValuation();
        BigDecimal inflationRate = inflationClient.getInflationRate();

        SimpleValuation simpleValuation = valuationsService.getNewValue(inflationIndexedBondsValuation, inflationRate);
        InflationIndexedBondsValuation inflationValuation = (InflationIndexedBondsValuation) simpleValuation;

        if(modelPortfolioService.getAll().size()!=0) {
            InflationIndexedBondsInvestment inflationIndexedInvestment = new InflationIndexedBondsInvestment();
            InflationIndexedBondsInvestment lastIndexedInvestment = inflationIndexedBondsService.findTopByDate();
            InflationIndexedBondsInvestment lastIndexedBond = inflationIndexedBondsService
                    .findByType(inflationIndexedBondsValuation.getType());
            BigDecimal interestRate = lastIndexedBond.getInterestRate();
            BigDecimal entireValuation = inflationValuationService.getEntireValuation(
                    interestRate.add(inflationRate));

            SimpleInvestment simpleInvestment = investmentService
                    .getNewInvestment(inflationIndexedInvestment, lastIndexedInvestment, entireValuation);

            InflationIndexedBondsInvestment inflationInvestment = (InflationIndexedBondsInvestment) simpleInvestment;

            inflationValuation.setInterestsValuation(interestRate.add(inflationRate));

            inflationIndexedBondsService.saveInvestment(inflationInvestment);
        }
        inflationValuationService.saveBondsValuation(inflationValuation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/inflation/invest")
    public List<InflationIndexedBondsDto> getInvestmentInfo() {
        return inflationMapper.mapToInflationBondsInvestmentListDto(inflationIndexedBondsService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/inflation/value")
    public List<InflationValuationDto> getAllValuations() {
        return inflationMapper.mapToInflationBondsListDto(inflationValuationService.getAll());
    }
}
