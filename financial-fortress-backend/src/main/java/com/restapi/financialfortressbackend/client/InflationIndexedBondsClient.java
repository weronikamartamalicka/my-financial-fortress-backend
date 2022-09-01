package com.restapi.financialfortressbackend.client;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController()
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class InflationIndexedBondsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/bonds/indexed/rate")
    public BigDecimal getInflationIndexedBondsRate() {
        return new BigDecimal(1.25);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/bonds/indexed/period")
    public BigDecimal getInflationIndexedBondsPeriod() {
        return new BigDecimal(1);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/bonds/indexed/rate")
    public BigDecimal updateInflationIndexedBondsRate() {
        return new BigDecimal(1.25);
    }

}
