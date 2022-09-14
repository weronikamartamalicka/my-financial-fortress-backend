package com.restapi.financialfortressbackend.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "GOLD")
public class GoldInvestment {

    @Id
    @NotNull
    @GeneratedValue()
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "TYPE")
    private final String type = "Krugerrand 1/2 oz.";

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "PURCHASE_VALUATION")
    public final BigDecimal purchaseValuation = BigDecimal.valueOf(4667);

    @Column(name = "ENTIRE_VALUATION")
    private BigDecimal entireValuation;

    public GoldInvestment(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
