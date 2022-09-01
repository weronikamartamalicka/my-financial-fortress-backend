package com.restapi.financialfortressbackend.client;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController()
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class InflationClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/inflation")
    public BigDecimal getInflationRate() {
        return new BigDecimal(0.25);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/inflation")
    public BigDecimal updateInflationRate() {
        return new BigDecimal(0.25);
    }

}
