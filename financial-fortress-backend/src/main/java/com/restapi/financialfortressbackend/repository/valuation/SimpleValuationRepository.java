package com.restapi.financialfortressbackend.repository.valuation;

import com.restapi.financialfortressbackend.domain.valuation.SimpleValuation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface SimpleValuationRepository extends CrudRepository<SimpleValuation, Long> {

}
