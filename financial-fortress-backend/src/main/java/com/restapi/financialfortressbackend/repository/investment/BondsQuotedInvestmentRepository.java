package com.restapi.financialfortressbackend.repository.investment;

import com.restapi.financialfortressbackend.domain.investment.BondsQuotedOnTheMarketInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BondsQuotedInvestmentRepository extends CrudRepository<BondsQuotedOnTheMarketInvestment, Long> {
    Optional<List<BondsQuotedOnTheMarketInvestment>> findByType(String type);
    @Override
    List<BondsQuotedOnTheMarketInvestment> findAll();

}
