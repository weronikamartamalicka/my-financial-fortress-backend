package com.restapi.financialfortressbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

    @JsonProperty("accountnumber")
    public String accountnumber;

    @JsonProperty("appid")
    public String appid;

    @JsonProperty("pass")
    public Object pass;

    @JsonProperty("token")
    public String token;
}
