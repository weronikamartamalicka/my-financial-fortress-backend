package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.InflationClient;
import com.restapi.financialfortressbackend.domain.InflationIndexedBondsInvestment;
import com.restapi.financialfortressbackend.domain.InflationIndexedBondsValuation;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.domain.dto.InflationIndexedBondsDto;
import com.restapi.financialfortressbackend.domain.dto.InflationValuationDto;
import com.restapi.financialfortressbackend.mapper.InflationMapper;
import com.restapi.financialfortressbackend.service.InflationIndexedBondsService;
import com.restapi.financialfortressbackend.service.InflationValuationService;
import com.restapi.financialfortressbackend.service.ModelPortfolioService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

    @Scheduled(cron = "0 0 10 15 * *")
    @RequestMapping(method = RequestMethod.POST, value = "/inflation/value")
    public void saveNewValuation() {

        ZoneId z = ZoneId.of( "America/Montreal" ) ;

        InflationIndexedBondsValuation inflationIndexedBondsValuation = new InflationIndexedBondsValuation();
        inflationIndexedBondsValuation.setDate(LocalDateTime.now(z));
        BigDecimal inflationRate = inflationClient.getInflationRate();
        inflationIndexedBondsValuation.setValuation(inflationRate);
        inflationIndexedBondsValuation.setInterestsValuation(BigDecimal.valueOf(0));

        if(modelPortfolioService.getAll().size()!=0) {
            InflationIndexedBondsInvestment inflationIndexedInvestment = new InflationIndexedBondsInvestment();
            inflationIndexedInvestment.setDate(LocalDateTime.now(z));
            InflationIndexedBondsInvestment lastIndexedInvestment = inflationIndexedBondsService.findTopByDate();
            inflationIndexedInvestment.setQuantity(lastIndexedInvestment.getQuantity());
            inflationIndexedInvestment.setRedemptionDate(lastIndexedInvestment.getRedemptionDate());

            BigDecimal interestRate = inflationIndexedBondsService.findByType(inflationIndexedBondsValuation
                            .getType())
                    .getInterestRate();

            inflationIndexedBondsValuation.setInterestsValuation(interestRate.add(inflationRate));

            BigDecimal entireValuation = inflationValuationService.getEntireValuation(
                    interestRate.add(inflationRate));

            ModelPortfolioInvestment modelPortfolio = new ModelPortfolioInvestment();
            modelPortfolio.setDate(LocalDateTime.now(z));
            modelPortfolio = modelPortfolioService.copyPortfolioValues(modelPortfolio);

            inflationIndexedInvestment.setEntireValuation(entireValuation);
            modelPortfolio.setBondsIndexedValue(entireValuation);
            modelPortfolio = modelPortfolioService.calculatePercentageComposition(modelPortfolio);

            modelPortfolioService.saveModelPortfolio(modelPortfolio);
            inflationIndexedBondsService.saveInflationIndexedInvestment(inflationIndexedInvestment);
        }

        inflationValuationService.saveBondsValuation(inflationIndexedBondsValuation);
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
