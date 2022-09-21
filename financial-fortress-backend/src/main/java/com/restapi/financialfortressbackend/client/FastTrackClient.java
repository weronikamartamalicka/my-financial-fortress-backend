package com.restapi.financialfortressbackend.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Component
@RequiredArgsConstructor
public class FastTrackClient {
    private final FastTrackValidationClient validationClient;
    private static String API_ROOT;
    private static final String API_KEY = "80b494e1-792e-4f10-a727-9afa62c3e7e0";
    private static String API_TOKEN;
    private static String API_TICKER;
    private BigDecimal commissionRate;

    public BigDecimal getActualValidation(String investType) {
        API_TOKEN = validationClient.getApiToken();
        API_ROOT = "https://ftl.fasttrack.net/v1/data/";

        switch(investType) {
            case "10 Yr Gov Bond iShr Ix" :
                API_TICKER = "GLJ-X";
                this.commissionRate = BigDecimal.valueOf(20);
                return this.getApiValuation();

            case "MSCI China A DivAdj Ix" :
                API_TICKER = "M-CNA";
                this.commissionRate = BigDecimal.valueOf(25);
                return this.getApiValuation().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            case "BofAML AAA-A Emerging Markets Corporate Ix" :
                API_TICKER = "EEM-X";
                this.commissionRate = BigDecimal.valueOf(20);
                return this.getApiValuation();
        }
        return BigDecimal.valueOf(0);
    }

    public List<BigDecimal> getHistoricalValuation(String investType) {
        API_TOKEN = validationClient.getApiToken();
        API_ROOT = "https://ftl.fasttrack.net/v1/data";

        switch(investType) {
            case "10 Yr Gov Bond iShr Ix" :
                API_TICKER = "GLJ-X";
                return this.getApiHistoricalValuation();

            case "MSCI China A DivAdj Ix" :
                API_TICKER = "M-CNA";
                return this.getApiHistoricalValuation().stream()
                        .map(value -> value.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP))
                        .collect(Collectors.toList());

            case "BofAML AAA-A Emerging Markets Corporate Ix" :
                API_TICKER = "EEM-X";
                return this.getApiHistoricalValuation();
        }
        return Collections.emptyList();
    }

    public List<BigDecimal> getApiHistoricalValuation() {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_ROOT + API_TICKER + "/range?start=2021-AUG-29&end=2022-AUG-29"))
                .header("Content-Type", "application/json")
                .header("appid", API_KEY)
                .header("token", API_TOKEN)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> responseString = HttpClient.newHttpClient().send(
                    request, HttpResponse.BodyHandlers.ofString());
            String json = responseString.body();

            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            JsonArray dataRangeArray = jsonObject.getAsJsonArray("datarange");

            List<BigDecimal> priceList = new ArrayList<>();

            for (JsonElement jsonElement : dataRangeArray) {
                JsonObject object = (JsonObject) jsonElement;
                priceList.add(object.get("price").getAsBigDecimal().divide(
                        BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));
            }
            return priceList;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public BigDecimal getApiValuation() {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_ROOT + API_TICKER + "/range"))
                .header("Content-Type", "application/json")
                .header("appid", API_KEY)
                .header("token", API_TOKEN)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> responseString = HttpClient.newHttpClient().send(
                    request, HttpResponse.BodyHandlers.ofString());
            String json = responseString.body();

            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            JsonObject dataRange = jsonObject.getAsJsonArray("datarange").get(0).getAsJsonObject();
            return dataRange.get("price").getAsBigDecimal();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
}
