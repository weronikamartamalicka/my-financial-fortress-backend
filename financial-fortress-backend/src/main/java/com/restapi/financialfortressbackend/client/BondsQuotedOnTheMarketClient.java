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

    @RequestMapping(method = RequestMethod.GET, value = "/api/bonds/quoted/coupon")
    public BigDecimal getBondsQuotedOnTheMarketCoupon() {
        return new BigDecimal(0.3);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/bonds/quoted/period")
    public BigDecimal getBondsQuotedOnTheMarketPeriod() {
        return new BigDecimal(1);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/bonds/quoted/value")
    public BigDecimal updateBondsQuotedOnTheMarketValuation() {
        return new BigDecimal(1.25);
    }
}
