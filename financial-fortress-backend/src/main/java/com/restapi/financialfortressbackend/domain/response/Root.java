package com.restapi.financialfortressbackend.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Root {

    @JsonProperty("success")
    public boolean success;

    @JsonProperty("base")
    public String base;

    @JsonProperty("timestamp")
    public int timestamp;

    @JsonProperty("rates")
    public Rates rates;
}
