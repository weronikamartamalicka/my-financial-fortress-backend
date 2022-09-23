package com.restapi.financialfortressbackend.repository.investment;

import com.restapi.financialfortressbackend.domain.investment.GoldInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface GoldInvestmentRepository extends CrudRepository<GoldInvestment, Long> {
    Optional<List<GoldInvestment>> findByType(String type);
    List<GoldInvestment> findAll();

}
