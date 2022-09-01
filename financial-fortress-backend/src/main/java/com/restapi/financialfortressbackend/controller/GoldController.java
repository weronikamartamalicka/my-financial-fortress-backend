package com.restapi.financialfortressbackend.controller;

import com.restapi.financialfortressbackend.client.GoldClient;
import com.restapi.financialfortressbackend.domain.dto.GoldDto;
import com.restapi.financialfortressbackend.service.GoldValuationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@CrossOrigin(value = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class GoldController {

    private final GoldClient goldClient;
    private final GoldValuationService goldValuationService;

    @RequestMapping(method = RequestMethod.POST, value = "/gold")
    public void openInvestment() {

        goldClient.getGoldPurchaseValue();
        goldValuationService.getOneCoinValue(goldClient.getGoldSaleValue());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gold/{investmentId}")
    public GoldDto getInvestment(@PathVariable Long investmentId) {
        return new GoldDto();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gold")
    public List<GoldDto> getInvestments() {
        return new ArrayList<>();
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/gold/{investmentId}")
    public void deleteInvestment(@PathVariable Long investmentId) {

    }
}
