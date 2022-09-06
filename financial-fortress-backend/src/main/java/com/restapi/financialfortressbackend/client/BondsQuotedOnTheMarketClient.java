package com.restapi.financialfortressbackend.client;

import com.restapi.financialfortressbackend.domain.dto.BondsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BondsQuotedOnTheMarketClient {

    private final RestTemplate restTemplate;
    private static final String API_ROOT = "https://ftl.fasttrack.net/v1/";
    private static final String API_KEY = "80b494e1-792e-4f10-a727-9afa62c3e7e0";
    private static final String API_TOKEN = "FB9D02A8-936B-4405-A607-BC20C45143AC";
    private static final String ticket = "GLJ-X";

    public BigDecimal getBondsQuotedOnTheMarketValuation() {

        URI url = HttpRequest.newBuilder()
                .uri(URI.create("https://ftl.fasttrack.net/v1/data/GLJ-X/range"))
                .header("Content-Type", "application/json")
                .header("appid", API_KEY)
                .header("token", API_TOKEN)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build()
                .uri();

        try {
            BondsResponse bondsResponse = restTemplate.getForObject(url, BondsResponse.class);
            int size = bondsResponse.getDatarange().size();
            List<BigDecimal> priceList =  Optional.ofNullable(bondsResponse)
                    .orElse(new BondsResponse())
                    .getDatarange().stream()
                    .map(datarange -> BigDecimal.valueOf(datarange.getPrice()))
                    .map(bigDecimal -> bigDecimal.divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP))
                    .collect(Collectors.toList());
            return priceList.get(size - 1);
        } catch(RestClientException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    public List<BigDecimal> getYearBondsValuation() {

        URI url = HttpRequest.newBuilder()
                .uri(URI.create("https://ftl.fasttrack.net/v1/data/GLJ-X/range?start=2021-AUG-29"))
                .header("Content-Type", "application/json")
                .header("appid", "80b494e1-792e-4f10-a727-9afa62c3e7e0")
                .header("token", "FB9D02A8-936B-4405-A607-BC20C45143AC")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build()
                .uri();

        try {
            BondsResponse[] bondsResponse = restTemplate.getForObject(url, BondsResponse[].class);
            return Optional.ofNullable(bondsResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .flatMap(stocksResponse -> stocksResponse.getDatarange().stream())
                    .map(datarange -> BigDecimal.valueOf(datarange.getPrice()))
                    .map(bigDecimal -> bigDecimal.divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP))
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
