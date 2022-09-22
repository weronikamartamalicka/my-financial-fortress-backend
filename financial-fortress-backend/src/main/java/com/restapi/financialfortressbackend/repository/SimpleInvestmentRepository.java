package com.restapi.financialfortressbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SimpleInvestmentRepository extends CrudRepository<SimpleInvestmentRepository, Long> {
}
