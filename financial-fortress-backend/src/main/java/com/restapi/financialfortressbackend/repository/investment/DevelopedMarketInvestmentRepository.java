package com.restapi.financialfortressbackend.repository.investment;

import com.restapi.financialfortressbackend.domain.investment.DevelopedMarketStocksInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface DevelopedMarketInvestmentRepository extends CrudRepository<DevelopedMarketStocksInvestment, Long> {
    Optional<List<DevelopedMarketStocksInvestment>> findByType(String type);
    List<DevelopedMarketStocksInvestment> findAll();
}
