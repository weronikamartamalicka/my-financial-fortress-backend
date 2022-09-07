package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketValuation;
import com.restapi.financialfortressbackend.domain.InflationIndexedBondsValuation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface BondsQuotedValuationRepository extends CrudRepository<BondsQuotedOnTheMarketValuation, LocalDate> {

    @Override
    List<BondsQuotedOnTheMarketValuation> findAll();

}
