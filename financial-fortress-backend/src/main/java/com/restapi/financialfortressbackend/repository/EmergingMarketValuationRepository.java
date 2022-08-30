package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.EmergingMarketStocksValuation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
@Repository
@Transactional
public interface EmergingMarketValuationRepository extends CrudRepository<EmergingMarketStocksValuation, LocalDate> {

    EmergingMarketStocksValuation findByDate(LocalDate date);
}
