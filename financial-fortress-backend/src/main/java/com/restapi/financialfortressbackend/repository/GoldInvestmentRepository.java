package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.investment.GoldInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface GoldInvestmentRepository extends CrudRepository<GoldInvestment, Long> {

    @Override
    GoldInvestment save(GoldInvestment goldInvestment);

    @Override
    Optional<GoldInvestment> findById(Long id);

    @Override
    List<GoldInvestment> findAll();

    @Override
    long count();

    Optional<List<GoldInvestment>> findByType(String type);


}
