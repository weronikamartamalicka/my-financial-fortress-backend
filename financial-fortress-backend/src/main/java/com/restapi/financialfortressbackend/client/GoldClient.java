package com.restapi.financialfortressbackend.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.restapi.financialfortressbackend.config.ClientConfiguration;
import com.restapi.financialfortressbackend.domain.response.Root;
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

@Component
@RequiredArgsConstructor
public class GoldClient {

    private final ClientConfiguration clientConfiguration;
    private final RestTemplate restTemplate;
    private static final String API_ROOT = "https://api.metalpriceapi.com/v1/";

    public Root getGoldSaleValue() {

        URI url = UriComponentsBuilder.fromHttpUrl(API_ROOT + "latest")
                .queryParam("api_key", clientConfiguration.getGOLD_CLIENT_APIKEY())
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

    public List<BigDecimal> getYearGoldSaleValue() {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_ROOT + "timeframe" + "?api_key="
                        + clientConfiguration.getGOLD_CLIENT_APIKEY() + "&start_date=2021-09-29" +
                        "&end_date=2022-08-20" + "&base=PLN" + "&currencies=XAU"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> responseString = HttpClient.newHttpClient().send(
                    request, HttpResponse.BodyHandlers.ofString());
            String json = responseString.body();

            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            JsonObject rates = jsonObject.getAsJsonObject("rates");
            JsonArray dataRangeArray = rates.getAsJsonObject().getAsJsonArray();

            List<BigDecimal> priceList = new ArrayList<>();

            for (JsonElement jsonElement : dataRangeArray) {
                JsonObject object = (JsonObject) jsonElement;
                priceList.add(object.get("XAU").getAsBigDecimal());
            }
            return priceList;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
