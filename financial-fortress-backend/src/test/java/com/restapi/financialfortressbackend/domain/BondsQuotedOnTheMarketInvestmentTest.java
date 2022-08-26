package com.restapi.financialfortressbackend.domain;

import com.restapi.financialfortressbackend.repository.BondsQuotedOnTheMarketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
class BondsQuotedOnTheMarketInvestmentTest {

    @Autowired
    private BondsQuotedOnTheMarketRepository bondsQuotedRepository;

    BondsQuotedOnTheMarketInvestment bondsQuoted1;
    BondsQuotedOnTheMarketInvestment bondsQuoted2;
    BondsQuotedOnTheMarketInvestment bondsQuoted3;
    List<BondsQuotedOnTheMarketInvestment> list;

    @BeforeEach
    public void prepareData() {
        bondsQuoted1 = new BondsQuotedOnTheMarketInvestment(
                new BigDecimal(100), new BigDecimal(1.5), new BigDecimal(0.3), LocalDateTime.now(),
                LocalDateTime.of(2022, 06, 03, 12, 55),LocalDateTime.now(), new BigDecimal(500));

        bondsQuoted2 = new BondsQuotedOnTheMarketInvestment(
                new BigDecimal(200), new BigDecimal(1.5), new BigDecimal(0.3), LocalDateTime.now(),
                LocalDateTime.of(2022, 06, 05, 12, 55),LocalDateTime.now(), new BigDecimal(500));

        bondsQuoted3 = new BondsQuotedOnTheMarketInvestment(
                new BigDecimal(300), new BigDecimal(1.5), new BigDecimal(0.3), LocalDateTime.now(),
                LocalDateTime.of(2022, 07, 05, 12, 55)
                ,LocalDateTime.now(), new BigDecimal(500));

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

    @Test
    public void shouldFindBondValueByDate() {
        bondsQuotedRepository.save(bondsQuoted1);
        bondsQuotedRepository.save(bondsQuoted2);
        bondsQuotedRepository.save(bondsQuoted3);

        LocalDateTime date = bondsQuoted1.getDate();

        Optional<BondsQuotedOnTheMarketInvestment> fetchedBond = bondsQuotedRepository.findByDate(date);

        assertEquals(date, fetchedBond.get().getDate());

        try {
            bondsQuotedRepository.deleteAll();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}