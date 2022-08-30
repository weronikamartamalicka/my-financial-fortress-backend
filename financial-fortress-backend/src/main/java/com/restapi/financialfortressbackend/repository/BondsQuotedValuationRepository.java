package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketValuation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
@Transactional
public interface BondsQuotedValuationRepository extends CrudRepository<BondsQuotedOnTheMarketValuation, LocalDate> {

    BondsQuotedOnTheMarketValuation findByDate(LocalDate date);
}
