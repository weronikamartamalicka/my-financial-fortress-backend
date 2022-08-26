package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.domain.dto.ModelPortfolioDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class ModelPortfolioController {

    @RequestMapping(method = RequestMethod.POST, value = "/portfolio", consumes = APPLICATION_JSON_VALUE)
    public void openInvestment(@RequestBody ModelPortfolioDto modelPortfolioDto) {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/portfolio/{portfolioId}")
    public ModelPortfolioDto getInvestment(@PathVariable Long portfolioIdId) {
        return new ModelPortfolioDto(
                1L,LocalDateTime.now(), new BigDecimal(5), new BigDecimal(5),
                new BigDecimal(5), new BigDecimal(5), new BigDecimal(5),
                new BigDecimal(5), new BigDecimal(5), new BigDecimal(5),
                new BigDecimal(5),  new BigDecimal(5), new BigDecimal(5)
        );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/portfolio")
    public List<ModelPortfolioDto> getInvestments() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/portfolio", consumes = APPLICATION_JSON_VALUE)
    public ModelPortfolioDto updateInvestment(@RequestBody ModelPortfolioDto modelPortfolioDto) {
        return new ModelPortfolioDto(
                1L,LocalDateTime.now(), new BigDecimal(5), new BigDecimal(5),
                new BigDecimal(5), new BigDecimal(5), new BigDecimal(5),
                new BigDecimal(5), new BigDecimal(5), new BigDecimal(5),
                new BigDecimal(5),  new BigDecimal(5), new BigDecimal(5)
        );
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/portfolio/{portfolioId}")
    public void deleteInvestment(@PathVariable Long portfolioIdId) {

    }
}
