package com.restapi.financialfortressbackend.domain;

import lombok.Getter;

@Getter
public enum InvestmentInstrumentName {

    BONDS_INDEXED("ROD0934"), BONDS_QUOTED("10 Yr Gov Bond iShr Ix"),
    DEVELOPED_ETF("MSCI China A DivAdj Ix"), EMERGING_ETF("BofAML AAA-A Emerging Markets Corporate Ix"),
    GOLD("Krugerrand 1/2 oz.");
    private String name;
    InvestmentInstrumentName(String name) {
        this.name = name;
    }
}
