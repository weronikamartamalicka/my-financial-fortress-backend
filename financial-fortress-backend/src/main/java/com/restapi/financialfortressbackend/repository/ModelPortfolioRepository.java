package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ModelPortfolioRepository extends CrudRepository<ModelPortfolioInvestment, Long> {
    @Override
    ModelPortfolioInvestment save(ModelPortfolioInvestment modelPortfolio);

    @Override
    Optional<ModelPortfolioInvestment> findById(Long id);

    @Override
    List<ModelPortfolioInvestment> findAll();

    ModelPortfolioInvestment findByDate(LocalDate date);
}
