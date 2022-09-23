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
        bondsQuoted1 = new BondsQuotedOnTheMarketInvestment(
                new BigDecimal(500),new BigDecimal(500), LocalDate.now(), BigDecimal.TEN
        );

        bondsQuoted2 = new BondsQuotedOnTheMarketInvestment(
                new BigDecimal(500),new BigDecimal(500), LocalDate.now(), BigDecimal.TEN
        );

        bondsQuoted3 = new BondsQuotedOnTheMarketInvestment(
                new BigDecimal(500),new BigDecimal(500), LocalDate.now(), BigDecimal.ONE
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

        List<BondsQuotedOnTheMarketInvestment> investmentList = bondsQuotedRepository.findAll();

        assertTrue(investmentList.size() == 3);

        try {
            bondsQuotedRepository.deleteAll();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}