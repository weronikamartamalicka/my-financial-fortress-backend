package com.restapi.financialfortressbackend.domain.valuation;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "INFLATION_INDEXED_BONDS_VALUATION")
public class InflationIndexedBondsValuation extends SimpleValuation {

    @Column(name = "INTERESTS_VALUATION")
    private BigDecimal interestsValuation;

    public InflationIndexedBondsValuation() {
        super("ROD0934");
    }
}
