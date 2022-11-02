package com.restapi.financialfortressbackend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ClientConfiguration {

    @Value("${exchange.api.key}")
    private String EXCHANGE_CLIENT_APIKEY;

    @Value("${fasttrack.api.key}")
    private String FAST_TRACK_CLIENT_APIKEY;

    @Value("${fasttrack.api.account}")
    private String FAST_TRACK_CLIENT_API_ACCOUNT;

    @Value("${fasttrack.api.password}")
    private String FAST_TRACK_CLIENT_API_PASSWORD;

    @Value("${gold.api.key}")
    private String GOLD_CLIENT_APIKEY;

    @Value("${inflation.api.key}")
    private String INFLATION_CLIENT_APIKEY;
}
