package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.InflationIndexedBondsInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface InflationIndexedBondsRepository extends CrudRepository<InflationIndexedBondsInvestment, Long> {
    @Override
    InflationIndexedBondsInvestment save(InflationIndexedBondsInvestment inflationIndexedBonds);

    @Override
    Optional<InflationIndexedBondsInvestment> findById(Long id);

    @Override
    List<InflationIndexedBondsInvestment> findAll();

    Optional<InflationIndexedBondsInvestment> findByDate(LocalDate date);
}
