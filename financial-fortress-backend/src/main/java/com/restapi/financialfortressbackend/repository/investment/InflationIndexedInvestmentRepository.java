package com.restapi.financialfortressbackend.repository.investment;

import com.restapi.financialfortressbackend.domain.investment.InflationIndexedBondsInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface InflationIndexedInvestmentRepository extends CrudRepository<InflationIndexedBondsInvestment, Long> {
    Optional<List<InflationIndexedBondsInvestment>> findByType(String type);
    List<InflationIndexedBondsInvestment> findAll();
}
