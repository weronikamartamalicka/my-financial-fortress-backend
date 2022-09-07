package com.restapi.financialfortressbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDate;
import java.util.HashMap;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatesMap {

    @JsonProperty("rates")
    private HashMap<LocalDate, XAG> ratesMap;
}
