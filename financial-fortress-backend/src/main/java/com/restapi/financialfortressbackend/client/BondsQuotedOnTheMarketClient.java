package com.restapi.financialfortressbackend.client;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController()
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class BondsQuotedOnTheMarketClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/bonds/quoted/value")
    public BigDecimal getBondsQuotedOnTheMarketValuation() {
        return new BigDecimal(1.25);
    }

}
