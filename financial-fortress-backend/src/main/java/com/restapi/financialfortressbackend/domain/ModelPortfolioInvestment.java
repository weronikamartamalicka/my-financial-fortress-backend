package com.restapi.financialfortressbackend.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MODEL_PORTFOLIO")
public class ModelPortfolioInvestment {

    @Id
    @NotNull
    @GeneratedValue()
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "VALUE")
    private BigDecimal entireValue;

    @Column(name = "GOLD_VALUE")
    private BigDecimal goldValue;

    @Column(name = "BONDS_QUOTED_VALUE")
    private BigDecimal bondsQuotedValue;

    @Column(name = "BONDS_INDEXED_VALUE")
    private BigDecimal bondsIndexedValue;

    @Column(name = "DEVELOPED_MARKET_VALUE")
    private BigDecimal developedMarketValue;

    @Column(name = "EMERGING_MARKET_VALUE")
    private BigDecimal emergingMarketValue;

    @Column(name = "GOLD_PERCENTAGE")
    private BigDecimal goldPercentage;

    @Column(name = "BONDS_QUOTED_PERCENTAGE")
    private BigDecimal bondsQuotedPercentage;

    @Column(name = "BONDS_INDEXED_PERCENTAGE")
    private BigDecimal bondsIndexedPercentage;

    @Column(name = "DEVELOPED_MARKET_PERCENTAGE")
    private BigDecimal developedMarketPercentage;

    @Column(name = "EMERGING_MARKET_PERCENTAGE")
    private BigDecimal emergingMarketPercentage;
}
