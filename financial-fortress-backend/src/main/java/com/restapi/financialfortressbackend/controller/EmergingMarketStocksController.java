package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.domain.dto.EmergingMarketStocksDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class EmergingMarketStocksController {

    @RequestMapping(method = RequestMethod.POST, value = "/market/emerging", consumes = APPLICATION_JSON_VALUE)
    public void openInvestment(@RequestBody EmergingMarketStocksDto emergingMarketDto) {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/market/emerging/{investmentId}")
    public EmergingMarketStocksDto getInvestment(@PathVariable Long investmentId) {
        return new EmergingMarketStocksDto(1L, new BigDecimal(5), LocalDateTime.now());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/market/emerging")
    public List<EmergingMarketStocksDto> getInvestments() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/market/emerging", consumes = APPLICATION_JSON_VALUE)
    public EmergingMarketStocksDto updateInvestment(@RequestBody EmergingMarketStocksDto emergingMarketDto) {
        return new EmergingMarketStocksDto(1L, new BigDecimal(5), LocalDateTime.now());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/market/emerging/{investmentId}")
    public void deleteInvestment(@PathVariable Long investmentId) {

    }
}
