package com.restapi.financialfortressbackend.repository.investment;

import com.restapi.financialfortressbackend.domain.investment.SimpleInvestment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface SimpleInvestmentRepository extends CrudRepository<SimpleInvestment, Long> {

}
