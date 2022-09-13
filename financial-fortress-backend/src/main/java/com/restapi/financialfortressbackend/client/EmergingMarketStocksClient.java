package com.restapi.financialfortressbackend.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.restapi.financialfortressbackend.domain.dto.response.LoginResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Getter
@Component
@RequiredArgsConstructor
public class EmergingMarketStocksClient {

    private final RestTemplate restTemplate;
    private static final String API_ROOT = "https://ftl.fasttrack.net/v1/";
    private static final String API_KEY = "80b494e1-792e-4f10-a727-9afa62c3e7e0";
    private String API_TOKEN;
    private static final String ticket = "EEM-X";

    public BigDecimal getCommissionValue() {
        return new BigDecimal(20);
    }

    public void setApiToken() {

        URI url = UriComponentsBuilder.fromHttpUrl(API_ROOT + "auth/login")
                .queryParam("account", "700671")
                .queryParam("appid", API_KEY)
                .queryParam("pass", "B0B760C8")
                .build()
                .encode()
                .toUri();
        try {
            LoginResponse loginResponse = restTemplate.getForObject(url, LoginResponse.class);
            String token = Optional.ofNullable(loginResponse)
                    .map(loginResponse1 -> loginResponse.getToken())
                    .orElse("736838183ABJBJSJ988");

            this.API_TOKEN = token;

        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getDayStockValuation() {

        this.setApiToken();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://ftl.fasttrack.net/v1/data/EEM-X/range"))
                .header("Content-Type", "application/json")
                .header("appid", API_KEY)
                .header("token", API_TOKEN)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> responseString = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String json = responseString.body();

            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            JsonObject dataRange = jsonObject.getAsJsonArray("datarange").get(0).getAsJsonObject();
            return dataRange.get("price").getAsBigDecimal();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    public List<BigDecimal> getYearStockValuation() {

        this.setApiToken();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://ftl.fasttrack.net/v1/data/EEM-X/range?start=2021-AUG-29&end=2022-AUG-29"))
                .header("Content-Type", "application/json")
                .header("appid", API_KEY)
                .header("token", API_TOKEN)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> responseString = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String json = responseString.body();

            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            JsonArray dataRangeArray = jsonObject.getAsJsonArray("datarange");

            List<BigDecimal> priceList = new ArrayList<>();

            Iterator iterator = dataRangeArray.iterator();
            while(iterator.hasNext()) {
                JsonObject object = (JsonObject)iterator.next();
                priceList.add(object.get("price").getAsBigDecimal());
            }
            return priceList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
