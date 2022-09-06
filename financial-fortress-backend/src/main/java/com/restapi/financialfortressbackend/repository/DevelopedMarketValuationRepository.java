package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.DevelopedMarketStocksValuation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface DevelopedMarketValuationRepository extends CrudRepository<DevelopedMarketStocksValuation, LocalDate> {

    DevelopedMarketStocksValuation findByDate(LocalDate date);

    @Override
    List<DevelopedMarketStocksValuation> findAll();
}
