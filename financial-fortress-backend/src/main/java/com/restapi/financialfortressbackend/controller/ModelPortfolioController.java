package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.domain.dto.ModelPortfolioDto;
import com.restapi.financialfortressbackend.exception.*;
import com.restapi.financialfortressbackend.service.ModelPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class ModelPortfolioController {

    @Autowired
    ModelPortfolioService modelPortfolioService;

    @RequestMapping(method = RequestMethod.POST, value = "/portfolio")
    public void openInvestment(@RequestBody BigDecimal investmentCapital) throws GoldNotFoundException,
            DevelopedMarketStocksNotFoundException, EmergingMarketStocksNotFoundException, ModelPortfolioNotFoundException, InflationIndexedNotFoundException, BondsQuotedNotFoundException {
        modelPortfolioService.calculateComposition(investmentCapital);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/portfolio/{portfolioId}")
    public ModelPortfolioDto getInvestment(@PathVariable Long portfolioIdId) {
        return new ModelPortfolioDto();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/portfolio")
    public List<ModelPortfolioDto> getInvestments() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/portfolio", consumes = APPLICATION_JSON_VALUE)
    public ModelPortfolioDto updateInvestment(@RequestBody ModelPortfolioDto modelPortfolioDto) {
        return new ModelPortfolioDto();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/portfolio/{portfolioId}")
    public void deleteInvestment(@PathVariable Long portfolioIdId) {

    }
}
