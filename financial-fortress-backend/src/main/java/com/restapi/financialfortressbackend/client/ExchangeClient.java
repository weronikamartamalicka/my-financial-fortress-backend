package com.restapi.financialfortressbackend.client;

import com.restapi.financialfortressbackend.domain.dto.response.ExchangeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExchangeClient {
    private final RestTemplate restTemplate;
    private static final String API_ROOT = "https://api.apilayer.com/exchangerates_data/convert";
    private static final String API_KEY = "TIf9r8k6RTG5hEFMJ8jAQJIrThEMQQ07";
    public BigDecimal getUSDToPLN() {

        URI url = UriComponentsBuilder.fromHttpUrl(API_ROOT + "?apikey=" + API_KEY)
                .queryParam("amount", "1")
                .queryParam("from", "USD")
                .queryParam("to", "PLN")
                .build()
                .encode()
                .toUri();
        try {
            ExchangeResponse exchangeResponse = restTemplate.getForObject(url, ExchangeResponse.class);
            Double exchangeResult = Optional.ofNullable(exchangeResponse).orElse(new ExchangeResponse()).getResult();
            return BigDecimal.valueOf(exchangeResult);

        } catch (RestClientException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
}
