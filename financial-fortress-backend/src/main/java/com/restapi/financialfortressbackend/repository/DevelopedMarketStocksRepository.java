package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.DevelopedMarketStocksInvestment;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
@Transactional
public interface DevelopedMarketStocksRepository extends CrudRepository<DevelopedMarketStocksInvestment, Long> {
    @Override
    DevelopedMarketStocksInvestment save(DevelopedMarketStocksInvestment developedMarketStocks);

    @Override
    Optional<DevelopedMarketStocksInvestment> findById(Long id);

    @Override
    List<DevelopedMarketStocksInvestment> findAll();

    Optional<DevelopedMarketStocksInvestment> findByDate(LocalDateTime date);
}
