package com.restapi.financialfortressbackend.domain.valuation;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "GOLD_VALUATION")
public class GoldValuation extends SimpleValuation {


    public GoldValuation() {
        super("Krugerrand 1/2 oz.");
    }
}
