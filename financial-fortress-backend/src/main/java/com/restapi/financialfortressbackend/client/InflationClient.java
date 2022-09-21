package com.restapi.financialfortressbackend.client;

import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@AllArgsConstructor
public class InflationClient {
    private static final String API_ROOT = "https://api.api-ninjas.com/v1/inflation/";
    private static final String API_KEY = "RBdwjG9WrITEvtdk4cP64g==Ze6uXem8LrKUydi0";

    public BigDecimal getInflationRate() {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_ROOT + "?country=Poland"))
                .header("Content-Type", "application/json")
                .header("X-Api-Key", API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> responseString = HttpClient.newHttpClient().send(request
                    , HttpResponse.BodyHandlers.ofString());
            String json = responseString.body();

            return JsonParser.parseString(json).getAsJsonArray()
                    .get(0)
                    .getAsJsonObject()
                    .get("yearly_rate_pct")
                    .getAsBigDecimal()
                    .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
}
