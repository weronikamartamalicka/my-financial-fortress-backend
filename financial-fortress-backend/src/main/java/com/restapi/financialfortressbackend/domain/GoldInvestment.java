package com.restapi.financialfortressbackend.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
@Entity(name = "GOLD")
public class GoldInvestment {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "TYPE")
    private static final String TYPE = "Krugerrand";

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "PURCHASE")
    private BigDecimal purchaseValuation;

    @Column(name = "SALE")
    private BigDecimal saleValuation;

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "ENTIRE_VALUE")
    private BigDecimal entireValue;

    public GoldInvestment(BigDecimal quantity, BigDecimal purchaseValuation, BigDecimal saleValuation,
                          LocalDateTime date, BigDecimal entireValue) {
        this.quantity = quantity;
        this.purchaseValuation = purchaseValuation;
        this.saleValuation = saleValuation;
        this.date = date;
        this.entireValue = entireValue;
    }
}
