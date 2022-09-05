package com.restapi.financialfortressbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StocksResponse {

    @JsonProperty("datarange")
    public ArrayList<Datarange> datarange;

    @JsonProperty("name")
    public String name;

    @JsonProperty("ticker")
    public String ticker;
}
