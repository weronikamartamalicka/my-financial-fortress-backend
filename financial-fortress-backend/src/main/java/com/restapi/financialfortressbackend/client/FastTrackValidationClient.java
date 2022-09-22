package com.restapi.financialfortressbackend.client;

import com.restapi.financialfortressbackend.domain.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FastTrackValidationClient {

    private final RestTemplate restTemplate;
    private static final String API_ROOT = "https://ftl.fasttrack.net/v1/auth/login";
    private static final String API_KEY = "80b494e1-792e-4f10-a727-9afa62c3e7e0";
    private static final String API_ACCOUNT = "700671";
    private static final String API_PASSWORD = "B0B760C8";

    public String getApiToken() {

        URI url = UriComponentsBuilder.fromHttpUrl(API_ROOT)
                .queryParam("account", API_ACCOUNT)
                .queryParam("appid", API_KEY)
                .queryParam("pass", API_PASSWORD)
                .build()
                .encode()
                .toUri();
        try {
            LoginResponse loginResponse = restTemplate.getForObject(url, LoginResponse.class);
            return Optional.ofNullable(loginResponse)
                    .map(loginResponse1 -> loginResponse.getToken())
                    .orElse("736838183ABJBJSJ988");

        } catch (RestClientException e) {
            e.printStackTrace();
            return "736838183ABJBJSJ988";
        }
    }
}
