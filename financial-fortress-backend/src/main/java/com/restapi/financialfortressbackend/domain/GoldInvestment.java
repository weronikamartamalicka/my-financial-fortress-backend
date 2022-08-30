package com.restapi.financialfortressbackend.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "GOLD")
public class GoldInvestment {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "TYPE")
    public static final String TYPE = "Krugerrand";

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    public GoldInvestment(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
