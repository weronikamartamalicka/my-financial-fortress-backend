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
@Entity(name = "BONDS_QUOTED_VALUATION")
public class BondsQuotedOnTheMarketValuation {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "DATE", unique = true)
    private LocalDateTime date;

    @Column(name = "TYPE")
    public final String type = "10 Yr Gov Bond iShr Ix";

    @Column(name = "VALUATION")
    private BigDecimal valuation;

    @Column(name = "COMMISSION")
    private BigDecimal commissionRate;

    @Column(name = "ENTIRE_VALUATION")
    private BigDecimal entireValuation;
}
