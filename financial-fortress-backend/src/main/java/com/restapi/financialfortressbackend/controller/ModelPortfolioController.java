package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.domain.InflationIndexedBondsInvestment;
import com.restapi.financialfortressbackend.domain.dto.ModelPortfolioDto;
import com.restapi.financialfortressbackend.mapper.ModelPortfolioMapper;
import com.restapi.financialfortressbackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@RestController()
@RequiredArgsConstructor
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class ModelPortfolioController {

    private final ModelPortfolioService modelPortfolioService;
    private final ModelPortfolioMapper modelPortfolioMapper;
    private final BondsQuotedOnTheMarketService bondsQuotedOnTheMarketService;
    private final InflationIndexedBondsService inflationIndexedBondsService;
    private final GoldInvestmentService goldInvestmentService;
    private final DevelopedMarketStocksService developedMarketStocksService;
    private final EmergingMarketStocksService emergingMarketStocksService;
    private final BondsQuotedValuationService bondsQuotedValuationService;
    private final InflationValuationService inflationValuationService;
    private final GoldValuationService goldValuationService;
    private final EmergingMarketValuationService emergingMarketValuationService;
    private final DevelopedMarketValuationService developedMarketValuationService;

    @RequestMapping(method = RequestMethod.POST, value = "/portfolio/{investmentCapital}")
    public void openInvestment(@PathVariable Long investmentCapital)  {
        modelPortfolioService.calculateComposition(BigDecimal.valueOf(investmentCapital));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/portfolio")
    public List<ModelPortfolioDto> getInvestments() {
        return modelPortfolioMapper.mapToModelPortfolioDtoList(modelPortfolioService.getAll());
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/portfolio")
    public void deleteInvestment() {
        modelPortfolioService.deleteAll();
        bondsQuotedOnTheMarketService.deleteAll();
        goldInvestmentService.deleteAll();
        inflationIndexedBondsService.deleteAll();
        emergingMarketStocksService.deleteAll();
        developedMarketStocksService.deleteAll();

        bondsQuotedValuationService.findTopByDate().setEntireValuation(BigDecimal.ZERO);
        goldValuationService.findTopByDate().setEntireValuation(BigDecimal.ZERO);
        inflationValuationService.findTopByDate().setEntireValuation(BigDecimal.ZERO);
        emergingMarketValuationService.findTopByDate().setEntireValuation(BigDecimal.ZERO);
        developedMarketValuationService.findTopByDate().setEntireValuation(BigDecimal.ZERO);
    }
}
