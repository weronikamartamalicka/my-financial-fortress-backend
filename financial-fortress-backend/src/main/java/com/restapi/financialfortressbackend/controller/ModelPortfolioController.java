package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.domain.dto.ModelPortfolioDto;
import com.restapi.financialfortressbackend.mapper.ModelPortfolioMapper;
import com.restapi.financialfortressbackend.service.ModelPortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RestController()
@RequiredArgsConstructor
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class ModelPortfolioController {

    private final ModelPortfolioService modelPortfolioService;
    private final ModelPortfolioMapper modelPortfolioMapper;

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
    }
}
