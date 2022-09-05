package com.restapi.financialfortressbackend.client;

import com.restapi.financialfortressbackend.domain.dto.Root;
import com.restapi.financialfortressbackend.domain.dto.StocksResponse;
import lombok.Getter;
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
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Component
@RequiredArgsConstructor
public class EmergingMarketStocksClient {

    private final RestTemplate restTemplate;
    private static final String API_ROOT = "https://ftl.fasttrack.net/v1/";
    private static final String API_KEY = "80b494e1-792e-4f10-a727-9afa62c3e7e0";
    private static final String API_TOKEN = "FB9D02A8-936B-4405-A607-BC20C45143AC";
    private static final String ticket = "EEM-X";

    public BigDecimal getCommissionValue() {
        return new BigDecimal(0.0029);
    }

    public BigDecimal getDayStockValuation() {

        URI url = HttpRequest.newBuilder()
                .uri(URI.create("https://ftl.fasttrack.net/v1/data/EEM-X/range"))
                .header("Content-Type", "application/json")
                .header("appid", API_KEY)
                .header("token", API_TOKEN)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build()
                .uri();

        try {
            StocksResponse developedStockResponse = restTemplate.getForObject(url, StocksResponse.class);
            int size = developedStockResponse.getDatarange().size();
            List<BigDecimal> priceList =  Optional.ofNullable(developedStockResponse)
                    .orElse(new StocksResponse())
                    .getDatarange().stream()
                    .map(datarange -> BigDecimal.valueOf(datarange.getPrice()))
                    .map(bigDecimal -> bigDecimal.divide(BigDecimal.valueOf(10), 2, RoundingMode.HALF_UP))
                    .collect(Collectors.toList());
            return priceList.get(size - 1);
        } catch (RestClientException e) {
            e.printStackTrace();
            return BigDecimal.ONE;
        }
    }

    public List<BigDecimal> getYearStockValuation() {

        URI url = HttpRequest.newBuilder()
                .uri(URI.create("https://ftl.fasttrack.net/v1/data/EEM-X/range?start=2021-AUG-29"))
                .header("Content-Type", "application/json")
                .header("appid", "80b494e1-792e-4f10-a727-9afa62c3e7e0")
                .header("token", "FB9D02A8-936B-4405-A607-BC20C45143AC")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build()
                .uri();

        try {
            StocksResponse[] developedStockResponse = restTemplate.getForObject(url, StocksResponse[].class);
            return Optional.ofNullable(developedStockResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .flatMap(stocksResponse -> stocksResponse.getDatarange().stream())
                    .map(datarange -> BigDecimal.valueOf(datarange.getPrice()))
                    .map(bigDecimal -> bigDecimal.divide(BigDecimal.valueOf(10), 2, RoundingMode.HALF_UP))
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public BigDecimal getExchangeCurrency() {
        return new BigDecimal(4.78);
    }



}
