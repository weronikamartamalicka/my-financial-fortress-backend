package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.domain.dto.BondsQuotedOnTheMarketDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class BondsQuotedOnTheMarketController {

    @RequestMapping(method = RequestMethod.POST, value = "/bonds/quoted", consumes = APPLICATION_JSON_VALUE)
    public void openInvestment(@RequestBody BondsQuotedOnTheMarketDto bondsQuotedDto) {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/bonds/quoted/{investmentId}")
    public BondsQuotedOnTheMarketDto getInvestment(@PathVariable Long investmentId) {
        return new BondsQuotedOnTheMarketDto();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bonds/quoted")
    public List<BondsQuotedOnTheMarketDto> getInvestments() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/bonds/quoted", consumes = APPLICATION_JSON_VALUE)
    public BondsQuotedOnTheMarketDto updateInvestment(@RequestBody BondsQuotedOnTheMarketDto bondsQuotedDto) {
        return new BondsQuotedOnTheMarketDto();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/bonds/quoted/{investmentId}")
    public void deleteInvestment(@PathVariable Long investmentId) {

    }
}
