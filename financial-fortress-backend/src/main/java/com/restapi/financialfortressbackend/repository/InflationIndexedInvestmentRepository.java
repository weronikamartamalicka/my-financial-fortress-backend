package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.investment.InflationIndexedBondsInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface InflationIndexedInvestmentRepository extends CrudRepository<InflationIndexedBondsInvestment, Long> {
    @Override
    InflationIndexedBondsInvestment save(InflationIndexedBondsInvestment inflationIndexedBonds);

    @Override
    Optional<InflationIndexedBondsInvestment> findById(Long id);

    @Override
    List<InflationIndexedBondsInvestment> findAll();

    Optional<List<InflationIndexedBondsInvestment>> findByType(String type);
}
