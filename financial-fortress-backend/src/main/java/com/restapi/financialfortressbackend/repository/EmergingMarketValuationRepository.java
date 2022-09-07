package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.EmergingMarketStocksValuation;
import com.restapi.financialfortressbackend.domain.InflationIndexedBondsValuation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface EmergingMarketValuationRepository extends CrudRepository<EmergingMarketStocksValuation, LocalDate> {

    @Override
    List<EmergingMarketStocksValuation> findAll();

}
