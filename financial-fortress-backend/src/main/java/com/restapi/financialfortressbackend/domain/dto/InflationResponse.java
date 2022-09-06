package com.restapi.financialfortressbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class InflationResponse {

    @JsonProperty("country")
    public String country;

    @JsonProperty("type")
    public String type;

    @JsonProperty("period")
    public String period;

    @JsonProperty("monthly_rate_pct")
    public double monthly_rate_pct;

    @JsonProperty("yearly_rate_pct")
    public double yearly_rate_pct;
}
