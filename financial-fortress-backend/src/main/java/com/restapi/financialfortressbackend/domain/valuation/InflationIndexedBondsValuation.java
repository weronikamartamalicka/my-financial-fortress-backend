package com.restapi.financialfortressbackend.domain.valuation;

import com.restapi.financialfortressbackend.domain.InvestmentInstrumentName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
@Table
@Getter
@Setter
@Entity(name = "INFLATION_INDEXED_BONDS_VALUATION")
public class InflationIndexedBondsValuation extends SimpleValuation {

    @Column(name = "INTERESTS_VALUATION")
    private BigDecimal interestsValuation;

    public InflationIndexedBondsValuation() {
        super(InvestmentInstrumentName.BONDS_INDEXED.getName());
    }
}
