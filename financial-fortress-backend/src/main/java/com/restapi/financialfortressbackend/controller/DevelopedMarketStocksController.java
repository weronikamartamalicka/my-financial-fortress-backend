package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.domain.dto.DevelopedMarketStocksDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class DevelopedMarketStocksController {

    @RequestMapping(method = RequestMethod.POST, value = "/market/developed", consumes = APPLICATION_JSON_VALUE)
    public void openInvestment(@RequestBody DevelopedMarketStocksDto developedMarketDto) {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/market/developed/{investmentId}")
    public DevelopedMarketStocksDto getInvestment(@PathVariable Long investmentId) {
        return new DevelopedMarketStocksDto(1L, new BigDecimal(5), LocalDateTime.now());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/market/developed")
    public List<DevelopedMarketStocksDto> getInvestments() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/market/developed", consumes = APPLICATION_JSON_VALUE)
    public DevelopedMarketStocksDto updateInvestment(@RequestBody DevelopedMarketStocksDto developedMarketDto) {
        return new DevelopedMarketStocksDto(1L, new BigDecimal(5), LocalDateTime.now());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/market/developed/{investmentId}")
    public void deleteInvestment(@PathVariable Long investmentId) {

    }
}
