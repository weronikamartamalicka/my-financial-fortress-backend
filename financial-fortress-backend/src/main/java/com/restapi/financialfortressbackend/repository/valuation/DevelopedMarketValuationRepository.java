package com.restapi.financialfortressbackend.repository.valuation;

import com.restapi.financialfortressbackend.domain.valuation.DevelopedMarketStocksValuation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface DevelopedMarketValuationRepository extends CrudRepository<DevelopedMarketStocksValuation, LocalDate> {

    @Override
    List<DevelopedMarketStocksValuation> findAll();
}
