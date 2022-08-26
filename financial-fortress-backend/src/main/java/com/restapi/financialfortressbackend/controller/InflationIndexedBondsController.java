package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.domain.dto.InflationIndexedBondsDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class InflationIndexedBondsController {

    @RequestMapping(method = RequestMethod.POST, value = "/bonds/indexed", consumes = APPLICATION_JSON_VALUE)
    public void openInvestment(@RequestBody InflationIndexedBondsDto inflationIndexedBondsDto) {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/bonds/indexed/{investmentId}")
    public InflationIndexedBondsDto getInvestment(@PathVariable Long investmentId) {
        return new InflationIndexedBondsDto(1L, LocalDateTime.now(), new BigDecimal(3));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bonds/indexed")
    public List<InflationIndexedBondsDto> getInvestments() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/bonds/indexed", consumes = APPLICATION_JSON_VALUE)
    public InflationIndexedBondsDto updateInvestment(@RequestBody InflationIndexedBondsDto inflationIndexedBondsDto) {
        return new InflationIndexedBondsDto(1L, LocalDateTime.now(), new BigDecimal(3));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/bonds/indexed/{investmentId}")
    public void deleteInvestment(@PathVariable Long investmentId) {

    }
}
