package com.restapi.financialfortressbackend.repository.investment;

import com.restapi.financialfortressbackend.domain.investment.EmergingMarketStocksInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EmergingMarketInvestmentRepository extends CrudRepository<EmergingMarketStocksInvestment, Long> {
    Optional<List<EmergingMarketStocksInvestment>> findByType(String type);
    List<EmergingMarketStocksInvestment> findAll();
}
