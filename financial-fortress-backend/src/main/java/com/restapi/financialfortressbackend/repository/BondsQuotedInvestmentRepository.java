package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.BondsQuotedOnTheMarketInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Repository
@Transactional
public interface BondsQuotedInvestmentRepository extends CrudRepository<BondsQuotedOnTheMarketInvestment, Long> {
    @Override
    BondsQuotedOnTheMarketInvestment save(BondsQuotedOnTheMarketInvestment bondsQuotedOnTheMarket);

    @Override
    Optional<BondsQuotedOnTheMarketInvestment> findById(Long id);

    @Override
    List<BondsQuotedOnTheMarketInvestment> findAll();

    BondsQuotedOnTheMarketInvestment findByType(String type);
}
