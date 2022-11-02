package com.restapi.financialfortressbackend.client;

import com.restapi.financialfortressbackend.config.ClientConfiguration;
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

    private final ClientConfiguration clientConfiguration;
    private final RestTemplate restTemplate;
    private static final String API_ROOT = "https://ftl.fasttrack.net/v1/auth/login";


    public String getApiToken() {

        URI url = UriComponentsBuilder.fromHttpUrl(API_ROOT)
                .queryParam("account", clientConfiguration.getFAST_TRACK_CLIENT_API_ACCOUNT())
                .queryParam("appid", clientConfiguration.getFAST_TRACK_CLIENT_APIKEY())
                .queryParam("pass", clientConfiguration.getFAST_TRACK_CLIENT_API_PASSWORD())
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
