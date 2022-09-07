package com.restapi.financialfortressbackend.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "EMERGING_MARKET_STOCKS_VALUATION")
public class EmergingMarketStocksValuation {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "DATE", unique = true)
    private LocalDateTime date;

    @Column(name = "TYPE")
    public final String type = "BofAML AAA-A Emerging Markets Corporate Ix";

    @Column(name = "VALUATION")
    private BigDecimal valuation;

    @Column(name = "COMMISSION")
    private BigDecimal commissionRate;


    @Column(name = "ENTIRE_VALUATION")
    private BigDecimal entireValuation;
}
