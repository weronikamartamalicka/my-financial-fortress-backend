package com.restapi.financialfortressbackend.client;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController()
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class GoldClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/gold/purchase")
    public void saveGoldPurchaseValue() {
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/gold/sale")
    void  saveGoldSaleValue() {
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/gold/purchase")
    public BigDecimal getGoldPurchaseValue() {
        return new BigDecimal(700);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/gold/sale")
    public BigDecimal getGoldSaleValue() {
        return new BigDecimal(800);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/gold/purchase")
    public BigDecimal updateGoldPurchaseValue() {
        return new BigDecimal(700);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/gold/sale")
    public BigDecimal updateGoldSaleValue() {
        return new BigDecimal(800);
    }
}
