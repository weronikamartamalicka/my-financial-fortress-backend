package com.restapi.financialfortressbackend.domain.valuation;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;

import javax.persistence.*;
@Table
@Entity(name = "GOLD_VALUATION")
public class GoldValuation extends SimpleValuation {

    public GoldValuation() {
        super(InvestmentInstrumentName.GOLD.getName());
    }
}
