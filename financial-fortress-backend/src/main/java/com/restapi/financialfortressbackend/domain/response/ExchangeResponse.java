package com.restapi.financialfortressbackend.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeResponse {

    @JsonProperty("date")
    public String date;

    @JsonProperty("result")
    public double result;

    @JsonProperty("success")
    public boolean success;
}
