package com.restapi.financialfortressbackend.domain;

import com.restapi.financialfortressbackend.domain.investment.BondsQuotedOnTheMarketInvestment;
import com.restapi.financialfortressbackend.repository.investment.BondsQuotedInvestmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
class BondsQuotedOnTheMarketInvestmentTest {

    @Autowired
    private BondsQuotedInvestmentRepository bondsQuotedRepository;

    BondsQuotedOnTheMarketInvestment bondsQuoted1;
    BondsQuotedOnTheMarketInvestment bondsQuoted2;
    BondsQuotedOnTheMarketInvestment bondsQuoted3;
    List<BondsQuotedOnTheMarketInvestment> list;

    @BeforeEach
    public void prepareData() {
        bondsQuoted1 = new BondsQuotedOnTheMarketInvestment("bonds", BigDecimal.valueOf(50),
                BigDecimal.valueOf(2), LocalDate.now()
        );

        bondsQuoted2 = new BondsQuotedOnTheMarketInvestment(
                "bonds", BigDecimal.valueOf(50),
                BigDecimal.valueOf(2), LocalDate.now()
        );

        bondsQuoted3 = new BondsQuotedOnTheMarketInvestment(
                "bonds", BigDecimal.valueOf(50),
                BigDecimal.valueOf(2), LocalDate.now()
        );

        list = new ArrayList<>();
        list.add(bondsQuoted1);
        list.add(bondsQuoted2);
        list.add(bondsQuoted3);
    }

    @Test
    public void shouldSaveBond() {
        bondsQuotedRepository.save(bondsQuoted1);
        Long id = bondsQuoted1.getId();
        Long id2 = bondsQuoted2.getId();
        Long id3 = bondsQuoted3.getId();

        try {
            bondsQuotedRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldFetchAllBondsRecords() {
        bondsQuotedRepository.save(bondsQuoted1);
        bondsQuotedRepository.save(bondsQuoted2);
        bondsQuotedRepository.save(bondsQuoted3);

        Long id = bondsQuoted1.getId();
        Long id2 = bondsQuoted2.getId();
        Long id3 = bondsQuoted3.getId();

        List<BondsQuotedOnTheMarketInvestment> investmentList = bondsQuotedRepository.findAll();

        System.out.println(investmentList.size());

        //assertTrue(investmentList.size() == 3);

        try {
            bondsQuotedRepository.deleteById(id);
            bondsQuotedRepository.deleteById(id2);
            bondsQuotedRepository.deleteById(id3);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}