package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.EmergingMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EmergingMarketStocksRepository extends CrudRepository<EmergingMarketStocksInvestment, Long> {

    @Override
    EmergingMarketStocksInvestment save(EmergingMarketStocksInvestment emergingMarketStocks);

    @Override
    Optional<EmergingMarketStocksInvestment> findById(Long id);

    @Override
    List<EmergingMarketStocksInvestment> findAll();

    Optional<EmergingMarketStocksInvestment> findByDate(LocalDateTime date);
}
