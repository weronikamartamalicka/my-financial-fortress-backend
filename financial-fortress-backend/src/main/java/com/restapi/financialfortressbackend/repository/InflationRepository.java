package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface InflationRepository extends CrudRepository<BigDecimal, Long> {
    @Override
    BigDecimal save(BigDecimal value);

    @Override
    Optional<BigDecimal> findById(Long id);

    @Override
    List<BigDecimal> findAll();

    Optional<BigDecimal> findByDate(LocalDateTime date);
}
