package com.restapi.financialfortressbackend.domain.valuation;

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
public abstract class SimpleValuation {

    @Id
    @NotNull
    @GeneratedValue()
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "DATE")
    protected LocalDateTime date;

    @Column(name = "TYPE")
    protected String type;

    @Column(name = "VALUATION")
    protected BigDecimal valuation;

    public SimpleValuation(String type) {
        this.type = type;
    }
}
