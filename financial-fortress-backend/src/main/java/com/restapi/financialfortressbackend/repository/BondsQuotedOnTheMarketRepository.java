package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketInvestment;
import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
@Transactional
public interface BondsQuotedOnTheMarketRepository extends CrudRepository<BondsQuotedOnTheMarketInvestment, Long> {
    @Override
    BondsQuotedOnTheMarketInvestment save(BondsQuotedOnTheMarketInvestment bondsQuotedOnTheMarket);

    @Override
    Optional<BondsQuotedOnTheMarketInvestment> findById(Long id);

    @Override
    List<BondsQuotedOnTheMarketInvestment> findAll();

    Optional<BondsQuotedOnTheMarketInvestment> findByDate(LocalDateTime date);
}
