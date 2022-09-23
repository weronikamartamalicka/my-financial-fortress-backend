package com.restapi.financialfortressbackend.domain.investment;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class SimpleInvestment {

    @Id
    @NotNull
    @GeneratedValue()
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "DATE")
    protected LocalDateTime date;

    @Column(name = "TYPE")
    protected String type;

    @Column(name = "QUANTITY")
    protected BigDecimal quantity;

    @Column(name = "ENTIRE_VALUATION")
    protected BigDecimal entireValuation;

    public SimpleInvestment(String type) {
        this.type = type;
    }

    public SimpleInvestment(String type, BigDecimal quantity) {
        this.type = type;
        this.quantity = quantity;
    }

}
