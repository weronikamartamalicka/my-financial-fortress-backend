package com.restapi.financialfortressbackend.client;

import com.restapi.financialfortressbackend.domain.dto.InflationResponse;
import com.restapi.financialfortressbackend.domain.dto.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InflationClient {

    private final RestTemplate restTemplate;
    private static final String API_ROOT = "https://api.api-ninjas.com/v1/inflation/";
    private static final String API_KEY = "RBdwjG9WrITEvtdk4cP64g==Ze6uXem8LrKUydi0";

    public BigDecimal getInflationRate() {

        URI url = UriComponentsBuilder.fromHttpUrl(API_ROOT + "?X-Api-Key=" + API_KEY)
                .queryParam("country", "Poland")
                .build()
                .encode()
                .toUri();

        try {
            InflationResponse inflationResponse = restTemplate.getForObject(url, InflationResponse.class);
            return BigDecimal.valueOf(
                    Optional.ofNullable(inflationResponse)
                            .orElse(new InflationResponse())
                            .getYearly_rate_pct())
                    .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
        } catch (RestClientException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
}
