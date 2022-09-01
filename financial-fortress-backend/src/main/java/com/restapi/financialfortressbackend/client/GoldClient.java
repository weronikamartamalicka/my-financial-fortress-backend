package com.restapi.financialfortressbackend.client;

import com.restapi.financialfortressbackend.domain.GoldValuation;
import com.restapi.financialfortressbackend.domain.dto.Root;
import com.restapi.financialfortressbackend.service.GoldInvestmentService;
import com.restapi.financialfortressbackend.service.GoldValuationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GoldClient {

    private final RestTemplate restTemplate;
    private static final String API_ROOT = "https://api.metalpriceapi.com/v1/latest";
    private static final String API_KEY = "0536b4c167ab37347f33927745026aab";

    public BigDecimal getGoldPurchaseValue() {
        return new BigDecimal(4667);
    }

    public Root getGoldSaleValue() {

        URI url = UriComponentsBuilder.fromHttpUrl(API_ROOT + "?api_key=" + API_KEY)
                .queryParam("base", "PLN")
                .queryParam("currencies", "XAU")
                .build()
                .encode()
                .toUri();

        try {
            Root goldResponse = restTemplate.getForObject(url, Root.class);
            return Optional.ofNullable(goldResponse).orElse(new Root());
        } catch (RestClientException e) {
            e.printStackTrace();
            return new Root();
        }
    }
}
