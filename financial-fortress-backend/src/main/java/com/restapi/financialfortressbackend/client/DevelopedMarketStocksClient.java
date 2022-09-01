package com.restapi.financialfortressbackend.client;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController()
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class DevelopedMarketStocksClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/market/developed/value")
    public BigDecimal getDevelopedMarketStockValuation() {
        return new BigDecimal(1.25);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/market/developed/commission")
    public BigDecimal getDevelopedMarketStockCommission() {
        return new BigDecimal(0.3);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/market/developed/value")
    public BigDecimal updateDevelopedMarketStockValuation() {
        return new BigDecimal(1.25);
    }
}
