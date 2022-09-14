package com.restapi.financialfortressbackend.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Entity(name = "EMERGING_MARKET_STOCKS")
public class EmergingMarketStocksInvestment {

    @Id
    @NotNull
    @GeneratedValue()
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "TYPE")
    public final String type = "BofAML AAA-A Emerging Markets Corporate Ix";

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "ENTIRE_VALUATION")
    private BigDecimal entireValuation;

    public EmergingMarketStocksInvestment(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
