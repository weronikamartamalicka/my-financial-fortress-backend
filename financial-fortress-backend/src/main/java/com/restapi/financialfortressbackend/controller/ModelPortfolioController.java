package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.domain.investment.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.domain.investment.dto.ModelPortfolioDto;
import com.restapi.financialfortressbackend.mapper.ModelPortfolioMapper;
import com.restapi.financialfortressbackend.service.*;
import com.restapi.financialfortressbackend.service.investment.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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


    @RequestMapping(method = RequestMethod.POST, value = "/portfolio/{investmentCapital}")
    public void openInvestment(@PathVariable Long investmentCapital)  {
        ModelPortfolioInvestment modelPortfolio = new ModelPortfolioInvestment();
        modelPortfolioService.calculateComposition(BigDecimal.valueOf(investmentCapital), modelPortfolio);
        modelPortfolioService.saveModelPortfolio(modelPortfolio);
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
    }
}
