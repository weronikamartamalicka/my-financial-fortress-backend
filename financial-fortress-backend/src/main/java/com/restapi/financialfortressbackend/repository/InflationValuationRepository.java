package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.InflationIndexedBondsValuation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
@Transactional
public interface InflationValuationRepository extends CrudRepository<InflationIndexedBondsValuation, LocalDate> {
    InflationIndexedBondsValuation findByDate(LocalDate date);
}
