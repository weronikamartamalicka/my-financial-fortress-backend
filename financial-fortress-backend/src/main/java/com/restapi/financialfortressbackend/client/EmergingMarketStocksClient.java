package com.restapi.financialfortressbackend.client;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController()
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class EmergingMarketStocksClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/market/emerging/value")
    public BigDecimal getEmergingMarketStockValuation() {
        return new BigDecimal(1.7);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/market/emerging/commission")
    public BigDecimal getEmergingMarketStockCommission() {
        return new BigDecimal(0.2);
    }
}
