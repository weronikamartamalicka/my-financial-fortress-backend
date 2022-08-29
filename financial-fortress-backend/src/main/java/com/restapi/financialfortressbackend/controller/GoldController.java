package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.domain.dto.GoldDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@CrossOrigin(value = "*")
@RequestMapping(value = "/v1")
public class GoldController {

    @RequestMapping(method = RequestMethod.POST, value = "/gold", consumes = APPLICATION_JSON_VALUE)
    public void openInvestment(@RequestBody GoldDto goldDto) {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/gold/{investmentId}")
    public GoldDto getInvestment(@PathVariable Long investmentId) {
        return new GoldDto(1L,LocalDate.now(), new BigDecimal(5));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gold")
    public List<GoldDto> getInvestments() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/gold", consumes = APPLICATION_JSON_VALUE)
    public GoldDto updateInvestment(@RequestBody GoldDto goldDto) {
        return new GoldDto(1L, LocalDate.now(), new BigDecimal(5));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/gold/{investmentId}")
    public void deleteInvestment(@PathVariable Long investmentId) {

    }
}
