package com.restapi.financialfortressbackend.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "INFLATION_INDEXED_BONDS_VALUATION")
public class InflationIndexedBondsValuation {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "TYPE")
    public final String type = "ROD0934";

    @Column(name = "DATE", unique = true)
    private LocalDate date;

    @Column(name = "VALUATION")
    private BigDecimal valuation;

    @Column(name = "INTERESTS_VALUATION")
    private BigDecimal interestsValuation;

    @Column(name = "ENTIRE_VALUATION")
    private BigDecimal entireValuation;

}
