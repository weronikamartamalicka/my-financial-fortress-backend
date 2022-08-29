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
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "MODEL_PORTFOLIO")
public class ModelPortfolioInvestment {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "REDEMPTION")
    private LocalDate redemptionDate;

    @Column(name = "DATE")
    private LocalDate date;

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

    public ModelPortfolioInvestment(LocalDate redemptionDate, LocalDate date, BigDecimal entireValue,
                                    BigDecimal goldValue, BigDecimal bondsQuotedValue, BigDecimal bondsIndexedValue,
                                    BigDecimal developedMarketValue, BigDecimal emergingMarketValue,
                                    BigDecimal goldPercentage, BigDecimal bondsQuotedPercentage,
                                    BigDecimal bondsIndexedPercentage, BigDecimal developedMarketPercentage,
                                    BigDecimal emergingMarketPercentage) {
        this.redemptionDate = redemptionDate;
        this.date = date;
        this.entireValue = entireValue;
        this.goldValue = goldValue;
        this.bondsQuotedValue = bondsQuotedValue;
        this.bondsIndexedValue = bondsIndexedValue;
        this.developedMarketValue = developedMarketValue;
        this.emergingMarketValue = emergingMarketValue;
        this.goldPercentage = goldPercentage;
        this.bondsQuotedPercentage = bondsQuotedPercentage;
        this.bondsIndexedPercentage = bondsIndexedPercentage;
        this.developedMarketPercentage = developedMarketPercentage;
        this.emergingMarketPercentage = emergingMarketPercentage;
    }
}
