package com.restapi.financialfortressbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoldResponse {

    @JsonProperty("success")
    public boolean success;

    @JsonProperty("base")
    public String base;

    @JsonProperty("timestamp")
    public int timestamp;

    @JsonProperty("rates")
    public List<Rates> rates;
}
